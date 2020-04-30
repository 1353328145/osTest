package com.jx.os.scheduling;

public class PCB {
    String name;//进程名
    int timeRequired;//运行完所需时间
    int timeNow;//当前已经运行的时间
    int priority;//优先级
    int state;//当前状态 0 wait 1 Run -1 finish
    public PCB(String name,int timeRequired,int priority){
        this.name=name;this.priority=priority;this.timeRequired=timeRequired;
        this.timeNow=0;
        this.state=0;
    }
}
