/**
 * isNaN
 * - 사람은 10진수, 컴퓨터는 2진수를 받아 들이므로 간극이 발생 👉 특히 소수점 표현시 문제 발생
 * - javascript 는 IEEE 754 표준 사용해서 표현함 (https://ko.wikipedia.org/wiki/IEEE_754)
 *  
 * isNaN // 느슨한 검사
 * Number.isNaN // 엄격한 검사 (ES2015)
 */

//javascript에서 사용하는 정수 판별
console.log(Number.MAX_SAFE_INTEGER); // 9007199254740991
console.log(Number.isInteger); 

// isNaN = is Not A Number, 숫자가 아니다. (검사 결과가 뒤집어서 not 나옴)
console.log(isNaN(123)); // 숫자가 숫자가 아니다.  

console.log(isNaN(123 + '테스트')); // true💩, javascript에서도 인정한 문제 
console.log(Number.isNaN(123 + '테스트')); // false, Number.isNaN() 으로 사용하기 권장✨ (살짝 이해 안됨)
