package com.jx.os.queue;

import com.jx.os.scheduling.PCB;

import java.util.LinkedList;
import java.util.Queue;

public class MFQS {
    private LinkedList<Process> tasks;//任务队列
    private LinkedList<Process> result;//结果队列
    private Queue<Process> [] queues;//反馈队列数组实例
    private int [] timeSlice;//反馈队列时间片分配
    private int processNum;//当前反馈队列任务数
    private int time;//时间变量
    public MFQS(LinkedList<Process> tasks){
        this.tasks=tasks;
        this.queues=new LinkedList[3];//初始化反馈队列
        queues[0]=new LinkedList<>();
        queues[1]=new LinkedList<>();
        queues[2]=new LinkedList<>();
        this.timeSlice=new int[3];//时间片分配
        this.result=new LinkedList<>();
        timeSlice[0]=1;
        timeSlice[1]=2;
        timeSlice[2]=4;
        this.processNum=0;
    }
    private void findStart(int time){
        if (!tasks.isEmpty()&&tasks.peek().startTime==time){//有没有要开始的进程
            Process process = tasks.poll();//出队
            queues[process.priority].offer(process);
            this.processNum++;
        }
    }
    public void run(){
        findStart(time);
        int slice=0;//当前时间片
        Process runNow=null;
        while(processNum!=0){
            if (slice==0){//时间片已经执行完
                int i=0;
                if (runNow!=null){//上次进程未执行完，加入下一队列
                    runNow.priority=runNow.priority!=2?runNow.priority+1:2;
                    queues[runNow.priority].offer(runNow);
                    runNow=null;
                }
                for (; i < queues.length; i++) {//从队列中拿出一个新的进程
                    if (!queues[i].isEmpty()){
                        runNow=queues[i].poll();
                        break;
                    }
                }
                if (runNow==null){//多级反馈队列已空
                    return;
                }
                slice=timeSlice[runNow.priority];
            }
            runNow.state=1;//进程运行
            runNow.timeNow++;//程序运行一个时间单位
            runNow.state=0;//运行结束
            if (runNow.timeNow==runNow.timeRequired){//进程执行完毕
                runNow.endTime=time;//记录进程结束的时间
                runNow.state=-1;//设置结束状态
                result.offer(runNow);
                processNum--;
                runNow=null;
                slice=0;
            }else{
                slice--;
            }
            time++;
            findStart(time);
        }
        print();
    }
    public void print(){
        System.out.println("进程名 进程结束时的队列 总需时间 周转时间 带权周转时间 状态");
        for (Process p : result) {
            System.out.printf("%-12s",p.name);
            System.out.printf("队列%-8d",p.priority+1);
            System.out.printf("%-7d",p.timeRequired);
            System.out.printf("%-8d",p.endTime);
            double T=p.endTime;
            double W=T/p.timeRequired;
            System.out.printf("%-8.2f",W);
            System.out.printf("%-4s",p.state==0?"wait":p.state==1?"run":"finish");
            System.out.println();
        }
    }
}
