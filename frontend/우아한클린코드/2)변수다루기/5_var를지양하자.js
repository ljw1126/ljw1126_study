/**
 * 5. var를 지양하자
 * 
 * let, const (예약어) : ES2015에 나옴 
 * 
 * 핵심
 * var : 함수 스코프 
 * let, const : 블록 소크프, TDZ 가짐  👉 안전하게 코드 작성가능함
 */

/*
 var name = '이름';
 var name = '이름2f'; 
 var name = '이름3';
 
 name
*/
 
// scope의 차이 가짐

let name = '이름3';
let name = '이름3'; // SyntaxError: Identifier 'name' has already been declared

name = '이름4';     // 재할당 가능 

const test = "test";    // const test; 선언 후 할당 안하면 에러 발생

console.log(test); 