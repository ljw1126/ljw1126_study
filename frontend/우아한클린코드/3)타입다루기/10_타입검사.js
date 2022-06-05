/**
 * 타입 검사 
 * 
 * typeof 연산자 
 * - 피연산자 확인 후 문자열로 반환해줌 
 * - 불변한 primitive 를 검사하는 용도
 * - 아래의 문제가 있기때문에 많은 주의 필요
 * 
 * 문제💩)
 * Primitive(원시) VS Reference (Object ex. Date, Array, etc )
 * - Primitive 타입은 typeof 로 타입 검사 가능하지만 
 *   Reference 타입은 typeof 로 타입 검사 어려움** 
 * 
 * 문제💩) typeof null ==> 'Objcet'
 * - javascript에서 인정한 언어적인 오류💩
 * 
 * 문제💩) 동적으로 변하는 언어이기 때문에 동적 타입까지 검사 x 🤦‍♂️
 * 
 * 
 * 👨‍💻 구글 검색 키워드 
 * javascript is function
 * javascript is array 
 * 등을 검색한 후 stack overflow에 따봉이 많은 글, 날짜 잘 보고 참고 하기  
 * 
 * 📝 정리 
 * javascript는 동적인 타입을 가지는 언어 -> 그래서 타입검사 어려움 
 * -> 그러므로 잘 찾아서 하기 -> 외우기는 어려우므로 주의해서 잘 찾아서 
 * 
 * 그리고 primitive와 reference 비교시 typeof || instanceof 가 무적 아니니 확인하고 하기 
 */



console.log(typeof '문자열'); //'string'
console.log(typeof true); //'boolean'
console.log(typeof undefined); //'undefined'
console.log(typeof 123); //'number'
console.log(typeof Symbol()); //'symbol'
console.log(typeof []); //'object'
console.log(typeof {}); //'object'

function myFunc() {};
console.log(typeof myFunc); // 'function'

class MyClass {};
console.log(typeof MyClass) // 'function'

const str = new String('test');// Wrapper class 사용하는 경우 
console.log(typeof str); // 'object'

console.log(typeof null); // 'object' , javascript에서 인정한 언어적인 오류


/**
 * 
 * instanceof 연산자 
 * - 객체의 프로토타입 체인 검사 
 * - Reference type 확인 용도 
 * 
 * 문) reference type의 프로토타입 찾아가다보면 최상위는 object 이기 때문에 검사 힘듦 
 */

function Person(name, age) {
    this.name = name;
    this.age = age;
}

const poko = new Person('poco', 99);

console.log(poko instanceof Person); // true 

const p = {
    name : 'poko',
    age : 99
}

console.log(p instanceof Person); // false

// reference type 
const arr = [];
const func = function () {};
const date = new Date();

console.log(arr instanceof Array); // true
console.log(func instanceof Function); // true
console.log(date instanceof Date); // true

console.log(arr instanceof Object); // true
console.log(func instanceof Object); // true
console.log(date instanceof Object); // true

// 다른 검사 방법👨‍💻
console.log(Object.prototype.toString.call(arr)); //[object Array]
console.log(Object.prototype.toString.call(func)); //[object Function]
console.log(Object.prototype.toString.call(date)); //[object Date]
console.log(Object.prototype.toString.call(new String('test'))); // [object String], wrapper class 가능

