/**
 * 배열 고차 함수 
 * 
 * 요구사항
 * 1. 원화 표기 
 * 2. 1000원 초과 리스트만 출력 
 * 3. 가격 순 정렬
 * 
 * 표준이 정해진 내장 메서드를 잘 활용하는게 명확해지고 좋다 
 * 
 */

const prices = ['2000', '1000', '3000', '5000', '4000'];

const suffixWon = (price) => price + '원';
const isOverOneThousand = (price) => Number(price) > 1000;
const ascendingList = (a, b) => a - b;

function getWonPrice(prices) {
    /*
    let temp = []; //💩 임시변수 줄여야 한다!

    for (let index = 0; index < prices.length; index++) {
        temp.push(prices[index] + '원');
    }

    return temp;
    */
    
    // better🧐 . Array 고차 함수 사용해서 변경 했지만, 조건이 늘어나면 지저분해질 수 있다.
    /*
    const isOverList = prices.filter(isOverOneThousand);
    const sortList = isOverList.sort(ascendingList);

    return sortList.map(suffixWon); // ✨ 동일 결과
    */
   
    // good. method chaining 활용 ✨
    // 체이닝 통해서 파이프라인처럼 순차적으로 명시됨
    return prices.filter(isOverOneThousand).sort(ascendingList).map(suffixWon);
}

console.log(getWonPrice(prices)); // [ '2000원', '3000원', '4000원', '5000원' ] 정상 동작
