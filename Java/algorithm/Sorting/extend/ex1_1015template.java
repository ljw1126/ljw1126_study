package extend;

import java.util.*;
import java.io.*;

public class ex1_1015template {

    static class Elem implements Comparable<Elem>{

        /*
            @param idx  >> A배열의 idx 위치를 기억하는 변수
            @param num  >> A[idx]의 원래 값 
        */
        public int num, idx; 

        @Override
        public int compareTo(Elem other){
            //TO DO 
            //정렬 조건에 맞게 정렬하기 
            // 1. num의 비내림차순
            // 2. num이 같으면 idx 오름차순
            return 0;
        }
    }
    
    static void pro(){
        // TODO : B배열 정렬하기 

        // TODO : B배열의 값을 이용해서 P 배열 채우기 

        // TODO : B배열 출력하기
    }
}
