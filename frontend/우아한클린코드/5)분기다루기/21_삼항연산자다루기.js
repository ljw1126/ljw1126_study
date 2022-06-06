/**
 * 삼항 연산자 다루기
 * 
 * 숏코딩을 위해 삼항 연산자를 사용하는데, 일관성이 중요 
 * 3개의 피연산자*를 취함  
 * 
 * 조건 ? 참 : 거짓 
 * 
 * 다음강의에서 다루는 내용인데
 * 조건 ? X : 거짓      // 삼항연산자로 거짓만 다룰 때  
 * 조건 ? 참 : X       // 삼항연산자로 참만 다룰 때 
 */

// case1. example() 둘다 동일한데, 둘다 가독성 떨어짐
// 🤔 switch case 문으로 바꾸는 걸, 고려하는 것을 추천✨ ( 현언에서 일반적으로 )
function example() { // 가독성 💩
    return condition1 ? value 
    : condition2 ? value2
    : condition3 ? value3 
    : value4;
}

function example() { // 가독성 💩
    if (condition1) { return value1; }
    else if(condition2) { return value2; }
    else if(condition3) { return value3; }
    else { return value4; }
}


// case2. 인덴트 넣어도 지저분..
// ✨괄호 > (장점)함수 , 우선순위 가질 수 있음
const example = condition1
    ? (a === 0 ? 'zero' : 'positive')
    : 'negative';


// case3.
// getName() nullable 하기 때문에 유용하게 null 처리 가능 ( if문 처리 가능 )    
const welcomeMessage = (isLogin) => {
    const name = isLogin ? getName() : '이름없음';

    return `안녕하세요 ${name}`;
};    

// case4. bad case 💩 , web API 함수 실행하는 방식 
function alertMessage(isAdult) {
    // alert 은 void 형이므로 return undefined와 같음 
    // isAdult ? undefined : undefined ( 숏코딩 ) 
    isAdult ? alert('입장 가능') : alert('입장 불가')
}

// better than 
function alertMessage(isAdult) {
    return isAdult ? '입장 가능' : '입장 불가';
}

function alertMessage(isAdult) {
   if (isAdult) {
       // some logic 
   }

   if (isAdult && gender === 'man') {

   } else if(isAdult && gender === 'woman') {

   }
}
