package com.jx.os.scheduling;

import java.util.ArrayList;
import java.util.List;

public class PSATest {
    public static void main(String[] args) {
        ArrayList<PCB> list=new ArrayList<>();
        list.add(new PCB("A进程",3,18));
        list.add(new PCB("B进程",4,21));
        list.add(new PCB("C进程",2,15));
        PSA psa=new PSA(list);
        psa.priorityScheduling();
    }
}
