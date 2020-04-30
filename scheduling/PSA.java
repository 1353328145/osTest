package com.jx.os.scheduling;

import java.util.ArrayList;
import java.util.Queue;

public class PSA {
    private ArrayList<PCB> PCBList;//待处理pcb集合
    public PSA(ArrayList<PCB> list){
        this.PCBList=list;
    }

    /**
     * 模拟优先级调度
     */
    public void priorityScheduling(){
        int time=0;                 //模拟时间
        PCB maxPCB = null;
        int lastMax=Integer.MIN_VALUE;
        int lastIndex=Integer.MIN_VALUE;
        while(!PCBList.isEmpty()){
            time++;
            boolean flag=true;
            int max=Integer.MIN_VALUE;
            int index=-1;
            for (int i=0;i<PCBList.size();i++) {//在队列中找出最大优先级进程
                if (max<PCBList.get(i).priority){
                    max=PCBList.get(i).priority;
                    maxPCB=PCBList.get(i);
                    index=i;
                }
            }
            if (max==lastMax){//和上次相等,仍执行上次进程
                flag = false;
                maxPCB=PCBList.get(lastIndex);
                index=lastIndex;
            }
            lastIndex=index;
            lastMax=max-2;
            assert maxPCB != null;
            maxPCB.timeNow++;       //当时进程运行一个时间单位
            maxPCB.state=1;         //正在运行
            print(time,index,flag);//打印当前状态
            maxPCB.state=0;//运行完毕
            for (int i = 0; i < PCBList.size(); i++) {
                if (i!=index){//其他元素等待优先级上升
                    PCBList.get(i).priority++;
                }
            }
            if (maxPCB.timeNow==maxPCB.timeRequired){//如果运行完毕 移除该进程
                PCBList.remove(index);
            }else{
                maxPCB.priority-=2;                    //未运行完毕，优先级-2;
            }
       }
    }
    private void print(int time,int index,boolean flag){
        int a=0;
        System.out.println("进程名 优先级 总需时间 已运行时间 状态 "+"第"+time+"单位时间");
        for (PCB p : PCBList) {
            System.out.printf("%-5s",p.name);
            System.out.printf("%-8d",p.priority);
            System.out.printf("%-9d",p.timeRequired);
            System.out.printf("%-8d",p.timeNow);
            System.out.printf("%-4s",p.state==0?"wait":p.state==1?"run":"finish");
            if (a==index){
                if (flag){
                    System.out.print("(正在运行,最大优先级改变进程，选择最大优先级进程 )");
                }else{
                    System.out.print("(正在运行)");
                }
            }
            System.out.println();
            a++;
        }
    }
}
