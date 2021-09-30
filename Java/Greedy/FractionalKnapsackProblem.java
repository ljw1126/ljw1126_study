/**
 * FractionalKnapsackProblem
  - 대표적인 그리디 알고리즘 문제 - 부분 배낭문제 ( Fractional Knapsack Problem) 
  - 무게 제한이 k인 배낭에 최대 가치를 가지도록 물건을 넣는 문제
  - 각 물건은 무게(w)와 가치(v)로 표현될 수 있음
    물건은 쪼갤 수 있으므로 물건의 일부분이 배낭에 넣어질 수 있음, 그래서 Fractional Knapsack Problem 으로 부름

※ 이때 객체에 대한 정렬은 Comparable또는 Comparator 인터페이스를 정의함 
   > 우선 순위는 COmparator가 더 높음 
   > 객체에 Comparable 인터페이스 상속시 compareTo() 오버라이딩 함수를 구현함 
   > 보통 Arrays.sort( , 여기 ) 또는 Collections.sort( , 여기) 에 Comparator 인터페이스 정의하며 
     compare() 오버라이딩 함수를 함 
 
*/

import java.util.*;

public class FractionalKnapsackProblem {
    // 탐욕(그리디) 알고리즘 
    public void knapsackFunc(Integer[][] objectList, double capacity){

        double totalValue = 0.0; // 총 가치 
        double fraction = 0 ;

        // 1. 최선의 해를 찾아야 하므로 먼저 데이터 배열을 정렬해줘야 함 
        //물건별 = { 무게(w), 가치(v) }
        // 선언 후 정렬 기준은 (가치 / 무게 ) => 1 , 0.8 , 0.5 , 0.32 , 0.16 
        Arrays.sort(objectList, new Comparator<Integer[]>() {
            double value1 = 0.0;
            double value2 = 0.0;
            @Override 
            public int compare(Integer[] item1, Integer[] item2 ){
                
                //return (item1[1] / item1[0] ) - (item2[1]/item2[0]);   // 나누기 하면 정수만 구해지니(0-0) 비교가 안됨 .. double 인데 
                //하나만 double로 캐스팅 하거나 , 값을 실수로 표현하면 됨 ( 둘 또는 하나만 )
                value1 = item1[1] / (double)item1[0];
                value2 = item2[1] / (double)item2[0];
                // 오름차순 정렬
                // if( value1 > value2){
                //     return 1;     
                // }else if(value1 < value2){
                //     return -1;
                // }else{
                //     return 0;
                // }

                //내림차순 정렬
                if( value1 > value2){
                    return -1;     
                }else if(value1 < value2){
                    return 1;
                }else{
                    return 0;
                }
            }
        });

        // 정렬 결과 확인 
        for(Integer[] item : objectList){
            System.out.println(Arrays.toString(item) + "/ 정렬기준 : " + (item[1]/(double)item[0]));
        }
 
        // 2. 정렬된 배열 기준으로 용량 빼주면서 최종 가치를 구함 
        for(int idx = 0; idx < objectList.length ; idx++){
            if( (capacity - objectList[idx][0]) > 0){ 
                // 기준 크기에서 정렬된 배열의 무게를 뺏을대 양수인 경우
                capacity -= (double)objectList[idx][0]; 
                totalValue += (double)objectList[idx][1]; // **최대 가치를 구하는 문제 
                System.out.println("1. 무게:" + objectList[idx][0] + ",가치:"+ objectList[idx][1]);
            }else{  
                // 음수인 경우 , 물건 분할해서 넣을 수 있다함 
                // **최대 가치를 구하는 문제 !!
                fraction = capacity / (double)objectList[idx][0]; // 남은 무게에 대해 물건을 나눠서 담을 수 있다 함 
                totalValue += (double)objectList[idx][1] * fraction; // 총 가치 = 물건의 가치 * 비율 
                System.out.println("2. 무게:" + objectList[idx][0] + ",가치:"+ objectList[idx][1]+ ",비율:" + fraction);
                break;
            } 
        }
        
        System.out.println("총 가치 : " + totalValue);

    }

    public static void main(String[] args) {
        //물건별 = { 무게(w), 가치(v) }
        // 선언 후 정렬 기준은 (가치 / 무게 ) => 1 , 0.16 , 0.8 , 0.5 , 0.32 
        Integer[][] objectList = {{10,10},{30,5},{15,12},{20,10},{25,8}};
        FractionalKnapsackProblem fkp = new FractionalKnapsackProblem();
        fkp.knapsackFunc(objectList, 30.0);
    }
}