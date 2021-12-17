package Sort;

import java.util.Arrays;

public class SelectionSort_Doit {
    
    static int[] a = {3,4,2,3,1};

    static void swap(int[] _a, int idx1, int idx2){
        int temp = _a[idx1];
        _a[idx1] = _a[idx2];
        _a[idx2] = temp;
    }

    static void selectionSort(int[] _a, int n){
        for(int i=0; i < n;i++){
            // min = a[i], ..., a[n-1]에서 가장 작은 값을 가지는 요소 
            // a[i] 와 a[min]의 값을 교환 
            int min = i;
            for(int j=n; j > i ; j--){
                if(a[min] > a[j]) min = j;
            }
            if(min != i) swap(_a, i, min);
        }

    }

    public static void main(String[] args) {
        selectionSort(a, a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
