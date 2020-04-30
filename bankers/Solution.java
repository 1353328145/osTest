package com.jx.os.bankers;

import java.util.Arrays;

/**
 * 银行家算法
 */
public class Solution {
    private int [][]max;//最大分配资源数
    private int [][]allocation;//已经分配资源数
    private int [][]need;//还需要资源数
    private int []available;//还剩资源数
    public Solution(int [][]max,int [][]allocation,int []available){
        this.max=max;
        this.allocation=allocation;
        this.available=available;
        this.need=new int[max.length][max[0].length];
        for (int i = 0; i < max.length; i++) {
            flushNeed(i);
        }
    }
    private void flushNeed(int i){//刷新某一行数据
        for (int j = 0; j < need[i].length; j++) {
            need[i][j]=max[i][j]-allocation[i][j];
        }
    }
    private boolean isGreater(int[] arr1,int[] arr2){//比较arr1是不是全部大于等于arr2
        boolean flag=true;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i]<arr2[i]){
                flag=false;
            }
        }
        return flag;
    }
    public boolean request(int index,int []req){
        if (req.length!=max[0].length){//输入错误
            return false;
        }
        if (isGreater(need[index],req)){//判断申请数组是否小于所有需求
            if (isGreater(available,req)){//判断申请数组是否小于现在有资源
                //尝试把资源分给进程index
                for (int i = 0; i <req.length; i++) {
                    allocation[index][i]+=req[i];
                }
                for (int i = 0; i < req.length; i++) {
                    available[i]-=req[i];
                }
                flushNeed(index);
                //进行安全性检查
                for (int[] n : need) {
                    if (isGreater(available,n)){//使剩余资源数满足可以一个need数组
                        System.out.print("请求资源成功!   ");
                        return true;
                    }
                }
                //处于不安全状态，还原
                for (int i = 0; i <req.length; i++) {
                    allocation[index][i]-=req[i];
                }
                for (int i = 0; i < req.length; i++) {
                    available[i]+=req[i];
                }
                flushNeed(index);
                System.out.print("请求后处于不安全状态，失败   ");
                return false;
            }else {
                System.out.print("请求数超过了现有资源数   ");
                return false;
            }
        }else{
            System.out.print("请求数超过了最大需求数   ");
            return false;
        }
    }
    public  void getNeed(){
        for (int[] ints : need) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
