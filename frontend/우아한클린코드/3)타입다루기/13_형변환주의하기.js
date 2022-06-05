/**
 * 형변환 주의하기
 * 
 * 참고. JS Comparison Table => 느슨한 검사 보다 엄격한 검사를 하는게 좋다..
 * https://dorey.github.io/JavaScript-Equality-Table/
 * 
 * 주의. parseInt( x , 진수) 
 * - default 진수가 10진수가 아니기 때문에 꼭 10진수 지정하기(오류방지)
 * 
 * 📝정리
 * - 사용자가 형변환했을때는 명시적인 변환이라 함
 * - 그리고 JS가 평가했을때 암묵적인 타입 변환 발생 가능 (좋지 않음, 에러 발생가능) 
 * - 고로 사람이 작성한 코드를 보고 명시적으로 예측 가능한 형변환을 할 것을 권고
 */

//느슨한 동등 비교시 암묵적인 형변환이 발생
console.log('1' == 1); // true, 

console.log(11 + ' 문자와 결합') // 11 문자와 결합
console.log(!!'문자열') // true
console.log(!!''); // false


//명시적 변환 => 좀 더 안전
console.log(String(11 + ' 문자와 결합')); // 11문자와 결합

console.log(Boolean('문자열')); // true
console.log(Boolean('')); // false
console.log(Number('11')); // 11

console.log(parseInt('9.999', 10)); // 9
