package Sort;
/*
    fastcampus 응용
*/
import java.util.*;

public class BubbleSort {
   
    // 변경 없는 경우 종료
    public ArrayList<Integer> sort(ArrayList<Integer> dataList){
        for(int i = 0 ; i < dataList.size() - 1; i++){
            boolean swap = false;
            
            for(int j = 0 ; j < dataList.size() -1-i; j++){
                if(dataList.get(j) > dataList.get(j+1)){
                    Collections.swap(dataList, j, j+1);
                    swap = true; 
                }
            }
            
            if (swap == false){
                break;
            }
        }
        
        return dataList;
    }
    
    //마지막 정렬된 인덱스 
    public ArrayList<Integer> sort2(ArrayList<Integer> dataList){
        int n = dataList.size();
        int k = 0; 
        while ( k < n-1 ) {
            int last = n -1;
            for(int i = n -1 ; i > k ; i--){
                if(dataList.get(i-1) > dataList.get(i) ){
                    Collections.swap(dataList, i, i-1);
                    last = i;
                }
            }
            k = last;
        }//end while 
        
        return dataList;
    }
    
    //쉐이커 기법 적용
    public ArrayList<Integer> shakerSort(ArrayList<Integer> dataList){
        int left = 0 ;
        int right = dataList.size() -1;
        int last = right;
        
        while(left < right){
            
            for(int i = right ; i > left ; i--){
                if(dataList.get(i-1) > dataList.get(i) ){
                    Collections.swap(dataList, i, i-1);
                    last = i;
                }
            }
            left = last;
            
            
            for(int j = left ; j < right ; j ++){
                if(dataList.get(j) > dataList.get(j+1)){
                    Collections.swap(dataList, j , j+1);
                    last = j ;
                }
            }
            right = last; 
        }
        
        return dataList;
    }

    
    public static void main(String[] args) {
        ArrayList<Integer> dataList = new ArrayList<>();

        for(int i = 0 ; i < 100 ; i++){
            dataList.add((int)(Math.random()*100));
        }

        BubbleSort bs = new BubbleSort();
        System.out.println(bs.sort(dataList));
        System.out.println(bs.sort2(dataList));
        System.out.println(bs.shakerSort(dataList));

    }
}

