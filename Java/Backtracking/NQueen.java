import java.util.ArrayList;

public class NQueen {
    
    // dfs 깊이 우선 탐색으로 stack(LIFO) 생각하기 ! 
    public void dfsFunc(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate){
        System.out.println("N : " + N + ", currentRow : " + currentRow + ", currentCandidate : " + currentCandidate);
       
        if(currentRow == N){ // 4 * 4 , 재귀호출로 4가 입력되면 
            System.out.println("결과 =====================");
            System.out.println(currentCandidate);
            return;     // 종료 
        }

        boolean flag ;
        for(int idx = 0 ; idx < N ; idx++){ // idx는 검사할 열 번호 뜻함 ( N = 4 일때 0~3까지 열있음 ) 
            flag = this.isAvailable(currentCandidate, idx); // currentCandidate.size()가 검사할 행 번호, idx가 열번호 
            System.out.println("isAvailable() ======= " +  flag);
            if(flag == true){
                currentCandidate.add(idx);
                this.dfsFunc(N, currentRow + 1, currentCandidate); // 행 증가 후 재귀
                currentCandidate.remove(currentCandidate.size() - 1); // this.dfsFunc() 재귀 함수 실행시 조건에 맞는게 없을때 currentCandidate 마지막값 삭제 , Pruning 기법 부분 
            }
        }
    }

    //배치가능 여부 확인 함수
    // candidate : 배치된 퀸 위치 정보
    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol){
        System.out.println("isAvailable=========================================");
        Integer currentRow = candidate.size(); // candidate.size()가 새로 퀸 추가할 행 번호 ( 0번행부터 시작)
        System.out.println("candidate : " + candidate);
        System.out.println("currentRow : " + currentRow);
        System.out.println("currentCol : " + currentCol);
        for(int idx = 0 ; idx < currentRow ; idx++){ // 앞에 배치완료된 퀸이 candidate에 차례대로 들어있음 
            
            // 수직인 경우와 대각선인 경우를 검사함 
            if( (candidate.get(idx) == currentCol) 
                || (Math.abs(candidate.get(idx) - currentCol) == currentRow - idx) ){ // promising 기법
                return false; 
            }
        }

        return true; 
    }
    
    
    public static void main(String[] args) {
        NQueen nq = new NQueen();
        /*
            N = N * N 행렬 표현  ( 4 * 4 )
            currentRow = 시작 행 번호
            currentCandidate = 퀸 배치 결과 저장되는 리스트(초기 [])
        */
        nq.dfsFunc(4,0, new ArrayList<Integer>()); 
        
    }    
}
