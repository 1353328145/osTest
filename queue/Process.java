package com.jx.os.queue;

public class Process {
    String name;//进程名
    int timeRequired;//运行完所需时间
    int timeNow;//当前已经运行的时间
    int priority;//进程所在队列
    int state;//当前状态 0 wait 1 Run -1 finish
    int turnaroundTime;//周转时间
    int startTime;//开始时间
    int endTime;
    public Process(String name,int timeRequired,int startTime){
        this.name=name;
        this.timeRequired=timeRequired;
        this.startTime=startTime;
        this.timeNow=0;this.priority=0;this.state=0;this.turnaroundTime=0;this.endTime=0;
    }
}
