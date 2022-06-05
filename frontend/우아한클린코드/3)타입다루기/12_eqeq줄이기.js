/**
 * eqeq 줄이기 
 * 
 * Equality(== 느슨한 동등연산자)
 * https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Equality
 * 
 * Strict equality(=== 엄격한 동등연산자) 
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Strict_equality
 * 
 * 참고. ESLint 활용해보기 
 * https://eslint.org/docs/rules/eqeqeq
 */

//동등연산자 그냥 사용시 type casting(형변환) 발생함 
console.log('1' == 1); // true , 이런 경우를 예상하고 느슨한 동등 연산자 사용은 위험하다..

//그래서 형변환을 사용해서 '엄격하게 (준수)하는게 좋다.' 🤔✨

const ticketNum = '1';

console.log(Number(ticketNum) === 1); // true