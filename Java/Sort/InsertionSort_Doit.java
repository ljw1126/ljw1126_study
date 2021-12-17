package Sort;

import java.util.*;
import java.io.*;

public class InsertionSort_Doit {
    
    static int[] a ={6,4,1,7,3,9,8};

    static void swap(int[] _a, int idx1, int idx2){
        int temp = _a[idx1];
        _a[idx1] = _a[idx2];
        _a[idx2] = temp;
    }
    /*

        i = 1               [6,4,1,7,3,9,8]
        j = 1 => 0 되고 a[j] = tmp; 되서 스왑됨 

                            [4,6,1,7,3,9,8]
        i = 2
        j = 2 => 0 까지 떨어졌을때 [4,4,6,7,3,9,8]
        a[j] = tmp되서 [1,4,6,7,3,9,8]

        i = 3 패스 

        i = 4 일때 
        j = 1 전까지 스왑 후 [1,3,4,6,7,9,8]

        
    */
    static void InsertionSort(int[] _a , int n){
        for(int i=1; i<n;i++){
            // temp <- a[i]
            // a[0] ~ a[i-1] 의 알맞은 곳에 tmp를 삽입함 
            /*  내가 한거
            for(int j=i;j>0;j--){
                if(_a[j] < _a[j-1]) swap(a, j, j-1);
            }
            */

            int j;
            int tmp = a[i]; // 비교대상 기준 
            for(j=i; j > 0 && a[j-1] > tmp; j--)
                a[j] = a[j-1];
            
            System.out.println("확인용:"+i+"번일때"+" , " + j);
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        InsertionSort(a , a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
