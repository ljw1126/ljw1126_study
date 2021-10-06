import java.util.ArrayList;

public class NQueen {
    
    //배치가능 여부 확인 함수
    // candidate : 배치된 퀸 위치 정보
    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol){
        System.out.println("isAvailable=========================================");
        Integer currentRow = candidate.size(); // 2개가 들어있으면 2번째 행 ( 0번행부터 시작)
        System.out.println("candidate : " + candidate);
        System.out.println("currentRow : " + currentRow);
        for(int idx = 0 ; idx < currentRow ; idx++){
            // 수직, 수평인 경우 와 대각선인 경우를 검사함 
            // 열의 차와 행의 차가 같은 경우 대각선 
            if( (candidate.get(idx) == currentCol) 
                || (Math.abs(candidate.get(idx) - currentCol) == currentRow - idx) ){ // promising 기법
                return false; 
            }
        }

        return true; 
    }
    
    public void dfsFunc(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate){
        if(currentRow == N){ // 4 * 4 , 재귀호출로 4가 입력되면 
            System.out.println("결과 =====================");
            System.out.println(currentCandidate);
            return;     // 종료 
        }

        for(int idx = 0 ; idx < N ; idx++){
            if(this.isAvailable(currentCandidate, idx) == true){
                currentCandidate.add(idx);
                this.dfsFunc(N, currentRow + 1, currentCandidate); // 행 증가 후 재귀
                currentCandidate.remove(currentCandidate.size() - 1); // 조건에 맞는게 없을대 가지 치기 , Pruning 기법 부분 
            }
        }
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
