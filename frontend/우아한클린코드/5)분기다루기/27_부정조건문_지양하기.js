/**
 * 부정 조건문 지양하기
 * 
 * NaN : Not A Number
 * 
 * why
 * 1. 생각을 여러번 해야 할 수 있다.(조건에 대해)
 * 2. 프로그래밍 언어 자체로 if문이 처음부터 오고 true 부터 실행시킨다. 
 * 3. 피곤할때 헷갈린다.. 명시적이지 않다. 😡
 * 
 * when use 부정 조건문
 * 1. Early return 
 * 2. Form validation 
 * 3. 보안 혹은 검사로직
 * 
 * 헷갈리는 코드보다 명시적인 코드를 사용하는게 좋다✨
 */

// bad. 생각을 여러번 해야 할 수 있다.(명시적이지 않다.)
if(isNaN(3)) {
    console.log('숫자입니다.');
}

// better
function isNumber(num) {
    return !Number.isNaN(num) && typeof num === 'number';
}

if (isNumber(3)) { // 애초에 if () 참은 조건이 들어가야 하는 명시적인 정의가 있는데 부정문 쓰면 헷갈리니 예제와 같이 함수로 뽑아 표현하는게 좋다.
    console.log('숫자입니다.');
}