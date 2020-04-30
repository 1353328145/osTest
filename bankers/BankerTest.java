package com.jx.os.bankers;

import java.util.Arrays;

public class BankerTest {
    public static void main(String[] args) {
        int [][]max={
                {7,5,3},
                {3,2,2},
                {9,0,2},
                {2,2,2},
                {4,3,3},
        };
        int [][]allocation={
                {0,1,0},
                {2,0,0},
                {3,0,2},
                {2,1,1},
                {0,0,2},
        };
        int []available={3,3,2};
        System.out.println("进程最大需求数   已分配资源数 剩余资源数"+Arrays.toString(available));
        for (int i = 0; i < max.length; i++) {
            System.out.print("进程"+(i+1)+"             ");
            System.out.print(Arrays.toString(max[i])+"  ");
            System.out.print(Arrays.toString(allocation[i]));
            System.out.println();
        }
        Solution solution=new Solution(max,allocation,available);
        int[] arr2={2,3,0};
        System.out.println("请求3进程 "+Arrays.toString(arr2));
        System.out.println(solution.request(4,arr2));
        int[] arr={1,2,2};
        System.out.println("请求2进程 "+Arrays.toString(arr));
        System.out.println(solution.request(1,arr));
    }
}
