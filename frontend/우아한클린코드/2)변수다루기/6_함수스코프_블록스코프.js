/**
case1
원인 : var는 함수 단위 scope이고 , if는 block 단위 scope이기 때문에 
참고 : https://yeoulcoding.me/155

var global = '전역'

if (global === '전역') {
    var global = '지역'      // var 사용시 전역 스코프와 함수 스코프 둘다 사용됨

    console.log(global); // 지역
}

console.log(global) // 지역  👉 문제. {} 안에서만 지역이 아니라 바깥에도 오염됨
*/


/**
 * case2. blcok단위의 let과 const으로 변경하는 경우
 * 
 * 안전하게 코딩가능해짐
 * 
 */
let global = '전역'

if (global === '전역') {
    let global = '지역'

    console.log(global); // 지역
}

console.log(global); //전역


/**
case3. var와 let의 차이 

{
    var globalAndBlock = 'hello';
    console.log(globalAndBlock); // hello
}

console.log(globalAndBlock); // hello


{
    let blockScope = 'test';
}

console.log(blockScope) // blockScope is not defined
*/

/**
 * 추천 : let보다 const 사용하기 
 * 👉 const는 재할당 금지이지만, 객체 || 배열 조작은 문제 없기때문
 */

const person = {
    name : 'hong',
    age : '30'
}

//에러. const 선언한 객체에 재할당 불가 Assignment to constatn variable
/*
    person = {
        name : 'lee',
        age : '34'
    }
*/

//통과. 재할당이 아니라서 에러 x  
person.name = 'lee'
person.age = '34'

console.log(person);   

const persons = [{
    name : 'tester',
    age : '22'
}]

persons.push({
    name : 'tester2',
    age : '33'
});

persons // [ { name: 'tester', age: '22' }, { name: 'tester2', age: '33' } ]