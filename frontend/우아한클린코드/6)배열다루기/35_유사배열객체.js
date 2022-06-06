/**
 * 유사 배열 객체 
 * 
 * arguments
 */

const arrayLikeObject = {
    0 : 'hello',
    1 : 'world',
    length : 2
};

// Array 객체로 변환
const arr = Array.from(arrayLikeObject);

console.log(arr);
console.log(Array.isArray(arr)); // true 

console.log(Array.isArray(arrayLikeObject)); // false

// 매개변수를 선언하지 않았음에도 불구하고 가변인자들을 읽을 수 있다.
// arguments : 유사 배열 객체 
function geneatePriceList() {

    console.log(Array.isArray(arguments)); // false, 유사배열 객체다

    for (let index = 0; index < arguments.length ; index++) { // 🧐 for문으로 배열 같은 객체 순회한것(__proto__ 아무것도 없음)
        const element = arguments[index];

        console.log(element); // 100, 200, 300 순차적으로 출력됨
    }

    //return arguments((arg) => arg + "원"); // arguments is not a function , 유사배열 객체라 Array 함수 x
    return Array.from(arguments).map((arg) => arg + "원"); // 뵨환후 정상동작
}

geneatePriceList(100, 200, 300);