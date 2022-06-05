/**
 * 전역 공간을 사용하지 말라! 최소화 하기 
 * 
 * 전역 공간 ?
 * - window : 브라우저 환경*에서 최상위, 브라우저의 자바스크립트 웹 API 명세가 모두 담겨있음
 * - global : node.js 환경*에서 최상위
 * 
 * 해당 내용을 피부로 느끼기 위해서는.
 * - IIFE : 즉시 실행함수 
 * - Module : 모듈
 * - Clousure
 * - const, let 사용 지양
 * 
 * 이유 
 * - 어디서나 접근가능 👉 사람이 보기에 파일로 분리되어 있는 것으로 보이나 런타임환경에서 window 객체 통해 접근가능하므로..
 * 
 * 그럼 어떻게?
 * - 전역변수 x 
 * - 지역변수만 만듦 
 * - window, global을 조작 x 
 * - const, let 을 사용하기 ! 
 * - IIFE(즉시 실행함수), module, clouse 등 스코프를 나누는 방법을 고민해보기 
 */

var globalVar = 'global';

console.log(globalVar);
// console.log(Window.globalVar); 문) 실제로 window 콘솔 찍어보니 해당 glovalVar가 있네.. 다른 js에서도 호출가능해지네?

/**
 * case💩. WebAPI인 setTimeout 을 사용해서 1초뒤에 '1초' 를 출력하고 싶으나 
 * 아래와 같이 var로 동일한 이름에 변수 할당한다거나, 함수 선언하면
 * 에러도 발생하지 않고, 결과가 덫씌워져 'function'이 출력하게 됨 
 * 
 * 👉브라우저 Web API 이기 때문에 javascript 작성과정에서는 에러 발생 되지 않고, 런타임엗서 에러 발생 가능 ! 💩
 */
setTimeout(() => {
    console.log('1초');    // 💩 function 출력됨
}, 1000);

//var setTimeout = 'setTimeout';   // 💩 얘가 선언되어 있으면 그냥 에러 

function setTimeout() {
    console.log('function');
}



