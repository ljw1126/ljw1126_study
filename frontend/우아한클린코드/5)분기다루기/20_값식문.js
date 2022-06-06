/**
 * 값식문
 * - 리액트로 설명해서 다음과 같은 것들이 있다정도만 알고 넘어가도 될 됫 
 * 
 * {
 *   변수 : if() ''     // 에러 
 *   변수 : 삼항연산자 ? true : false // 통과, 표현식은 값으로 귀결될 수 있음
 * }
 * 
 * 참고. 
 * - react 에서 JSX 문법으로 작성하면 bable 거쳐 transfiling 된다. 
 * 
 * 📝 각 기호마다 역할 다름 
 * () - '함수' 호출, 매개변수를 인자로 넘김
 * {} - 
 * [] - 
 * 
 *  
 */

// IIFE(즉시 실행함수)
{(() => {
    if(1) {
        return true
    } else {
        return false
    }
})()}

// A || B  
console.log(true || false); // true

// 고차함수 -- 배열 섹션에서 다룰 예정 