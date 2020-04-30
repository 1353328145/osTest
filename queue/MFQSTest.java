package com.jx.os.queue;

import java.util.LinkedList;

public class MFQSTest {
    public static void main(String[] args) {
        LinkedList<Process> list=new LinkedList<>();//就绪队列进入时间从小到大，不可能出现从大到小的情况
        list.add(new Process("进程A",4,0));
        list.add(new Process("进程B",2,1));
        list.add(new Process("进程C",3,2));
        list.add(new Process("进程D",6,3));
        MFQS mfqs=new MFQS(list);
        mfqs.run();
    }
}
