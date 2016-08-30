package com.fahai;

import org.junit.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by zhanghao on 16/8/29.
 */
public class Arithmetic {
    //如果存在返回数组位置，不存在则返回-1
    public int binarySearch(int[] arr, int len, int value){
        //如果传入的数组为空或者数组长度<=0那么就返回-1。防御性编程
        if(arr==null || len<=0)
            return -1;
        int start = 0;
        int end = len - 1;

        while(start <= end){
            int mid = start + (end - start)/2;
            if(arr[mid] == value)
                return mid;
            else if(value < arr[mid])
                end = mid-1;
            else
                start = mid + 1;
        }
        return -1;
    }
    //冒泡排序 时间复杂度:n^2
    //每一轮比较都从头开始,然后两两比较,如果左值比右值大,则交换位置,每一轮结束后,当前轮"最后一个元素"必将是最大的.
    @org.junit.Test
    public void bubbling(){
        int[] arr = {4, 3, 10, 6, 2};
        int size = arr.length;
        for(int i = 0; i < size-1; i++){
            for(int j = 0; j< size -i -1; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
    //插入排序 时间复杂度 n^2
    //把待排序的纪录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，直到所有的纪录插入完为止，得到一个新的有序序列
    @Test
    public void insertSort(){
        int[] arr = {4, 3, 10, 6, 2};
        int size = arr.length;
        for(int i=0; i < size-1; i++){
            for(int j=i; j>0; j--){
                if(arr[j] < arr[j-1]){
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }
    }
    //快速排序 时间复杂度 n*logN
    //通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
    public static void sort(int[] sources,int low,int high){
        if(low < high){
            int key = sources[low];//此轮比较的key,左边比key大,右边比key小.
            int l = low;
            int h = high;
            int tmp;
            while(l < h){
                //因为我们不能创建额外的数组,所以才取了"交换"数据的方式.
                //从右边开始,将比key大的交换到过来.
                while(l < h && sources[h] >= key){
                    h--;
                }
                //右边找到了比key大的.
                if(l < h){
                    //交换顺序
                    tmp = sources[l];
                    sources[l] = sources[h];
                    sources[h] = tmp;
                }
                //从左边开始,将比key小的交换过来
                while(l < h && sources[l] <= key){
                    l++;
                }
                if(l < h){
                    tmp = sources[l];
                    sources[l] = sources[h];
                    sources[h] = tmp;
                }
            }
            for (int i = 0; i < sources.length; i++) {
                System.out.print(sources[i] + " ");
            }
            System.out.println();

            sort(sources, low, l-1);
            sort(sources, l+1, high);
        }
    }



    public static void quictSort(int[] sources,int start,int end){
        if(start < end){
            int tmp;
            int i = start;
            int j = end;
            int value = sources[i];
            while(i < j){
                while(sources[j] > value ){
                    j--;
                }
                //右边找到了比key大的.
                tmp = sources[i];
                sources[i] = sources[j];
                sources[j] = tmp;

                while(sources[i] < value){
                    i++;
                }
                //左边找到了比key大的值
                tmp = sources[i];
                sources[i] = sources[j];
                sources[j] = tmp;
            }
            for (int x = 0; x < sources.length; x++) {
                System.out.print(sources[x] + " ");
            }
            System.out.println();

            quictSort(sources, start, j - 1);
            quictSort(sources, j + 1, end);
        }
    }

    //归并排序 时间复杂度 n*logN 空间复杂度 n
    //分治法,将数组逐步拆分为"组",直到最小的"组",然后每个组内排序,然后依次和相邻的组"排序合并",其中"排序".其内部排序非常简单.直接比较.
    public static void mergeSort(int[] sources,int begin,int end){
        if(begin < end){
            int range = end - begin;
            int mid = begin + range/2;
            mergeSort(sources, begin, mid);//左段
            mergeSort(sources, mid + 1, end);//右端
            System.out.println(Arrays.toString(sources));

            merge(sources, begin, mid, end);
        }
    }
    private static void merge(int[] sources,int begin,int mid,int end){
        int[] tmp = new int[end - begin + 1];
        int b1 = begin;
        int e1 = mid;
        int b2 = mid+1;
        int e2 = end;
        int i=0;
        for(;b1 <= e1 && b2 <= e2 ; i++){
            //填充tmp数组,并依此在两段数据区域中找到最小的
            if(sources[b1] <= sources[b2]){
                tmp[i] = sources[b1];
                b1++;
            }else{
                tmp[i] = sources[b2];
                b2++;
            }
        }
        //到此为止,两段数据区域,已经至少一个被扫描完毕
        if(b1 > e1){
            //如果b1~e1扫描完毕,那么可能b2~e2还有剩余
            for(int t = b2;t < e2 + 1; t++){
                tmp[i] = sources[t];
                i++;
            }
        }
        if(b2 > e2){
            //如果b2~e2扫描完毕,那么可能b1~e1还有剩余
            for(int t = b1;t < e1 + 1; t++){
                tmp[i] = sources[t];
                i++;
            }
        }
        //replace and fill:将tmp数组的数据,替换到source中,begin~end
        //因为此时tmp中的数据是排序好的
        i=0;
        for(int t= begin;t <= end; t++){
            sources[t] = tmp[i];
            i++;
        }
        tmp = null;//
    }
    public static void main(String[] args){
        int[] sources = {4,3,10,6,2};
        mergeSort(sources, 0, sources.length - 1);
        System.out.println(Arrays.toString(sources));

    }

}
