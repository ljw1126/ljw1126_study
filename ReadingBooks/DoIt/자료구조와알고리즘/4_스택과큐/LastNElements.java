import java.util.Scanner;

public class LastNElements {
    // 링 버퍼 = '오랜된 데이터를 버리는 용도'로 사용 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = 10;
        int[] a = new int[N]; // 입력받은 값을 저장할 배열
        int cnt = 0; // 입력받은 개수
        int retry;
        System.out.println("정수를 입력하세요.");

        do{
            System.out.printf(" %d번째 정수", cnt+1);
            a[cnt++ % N] = sc.nextInt();

            System.out.println("계속할까요? (예:1,아니오:0) ");
            retry = sc.nextInt();
        }while(retry == 1);

        // 반복문 종료 후 
        // a[0] ~ a[cnt-1]
        int i = cnt - N; 
        if (i<0) i = 0;
        //cnt=11일때, 10번째 정수(9+1)는 9번 인덱스값 , 11번째(10+1) 0번 인덱스값 
        for( ; i < cnt ; i++){
            System.out.printf("%2d번째 정수=%d\n", i+1, a[i%N]);
        }

    }
}
