package Sort;
import java.util.ArrayList;
import java.util.Collections;

public class InsertSort {
    
    public ArrayList<Integer> sort(ArrayList<Integer> list){
        int n = list.size();
        for(int i = 0 ; i < n-1 ; i ++){
            for(int j = i+1 ; j > 0 ; j--){
                if(list.get(j) < list.get(j-1))
                    Collections.swap(list, j, j-1);
                else 
                    break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testList = new ArrayList<>();
        for(int i = 0 ; i < 100 ; i++){
            testList.add((int)(Math.random()*100));
        }

        InsertSort is = new InsertSort();
        System.out.println(is.sort(testList)); // 결과 리턴
    }
}
