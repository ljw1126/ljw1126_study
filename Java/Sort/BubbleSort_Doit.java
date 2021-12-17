package Sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BubbleSort_Doit {
    
    // a[idx1]와 a[idx2]의 값을 바꿈
    static void swap(int[] a, int idx1, int idx2){
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    // 기본
    static void bubbleSort(int[] a, int n){
        for(int i=0;i<n-1;i++){ // 0~6
            for(int j=n-1; j > i ; j--){ // 6 ~ (0~6)
                if(a[j-1] > a[j]) {
                    swap(a, j-1, j);
                    System.out.println(Arrays.toString(a));
                }
            }
            System.out.println("===========================");
        }
    }

    // 개선 1 - 교환 횟수가 0인 경우 정렬 마쳤다는 거니 바로 종료
    static void bubbleSort_1(int[] a, int n){
        int exchg;
        for(int i=0; i < n-1; i++){
            exchg = 0; // 교환 횟수를 기록

            for(int j=n-1; j > i; j--){
                if(a[j-1] > a[j]) {
                    swap(a, j-1, j);
                    exchg++;
                    System.out.println(i+"번째:"+Arrays.toString(a));
                }
            }
            System.out.println("===========================");
            if(exchg == 0) break; // 교환이 이루어 지지 않으면 종료 
        }
    }

    // 개선 2 - 마지막 교환한 위치(k)로 범위를 조정
    static void bubbleSort_2(int[] a, int n){
        int k = 0; // 첫번째 패스에서는 모든 요소를 검사해야 하기때문에 0으로 초기화
        while(k < n -1){
            int last = n-1; // 마지막으로 요소를 교환한 위치 
            for(int j = n-1; j > k; j--){
                if(a[j-1] > a[j]) swap(a, j-1, j);
                last = j;
            }
            k = last;
        }
    }

    //{9,1,3,4,6,7,8} 이런 경우 양방향 버블 사용하는게 더 빠름 
    static void shakerSort(int[] a, int n){
        int left = 0;
        int right = a.length-1;
        int last = right;

        while(left < right){

            for(int i= right; i > left ; i--){
                if(a[i-1] > a[i]){
                    swap(a, i-1, i);
                    last = i;
                } 
            }

            left = last; //1 -> 5

            for(int j=left; j<right;j++){ // 5~5가 되면 동작 x 
                if(a[j] > a[j+1]){
                    swap(a, j,j+1);
                    last = j;
                }
            }

            right = last; //5 -> 5 
        }
    }

    public static void main(String[] args) {
        // 1. 값 설정
        int n = 7;
        int[] arr = {22,5,11,32,120,68,70};
        // 2. 버블 정렬 수행
        bubbleSort_1(arr,n);
        // 3. 결과 출력
        System.out.println("결과출력:"+Arrays.toString(arr));

        // 예제. 셰이커 정렬(shaker sort) , 양방향 버블 
        int[] qn = {9,1,3,4,6,7,8}; // 9가 맨앞에 있는데 한 사이클해도 한칸 밖에 이동안하니 비 효율적
        shakerSort(qn, 7);
        System.out.println(Arrays.toString(qn));
    }

    static class MyReader{
        BufferedReader br;
        StringTokenizer st;

        public MyReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    
}
