/**
 * 호이스팅 주의하기
 * 
 * - 코드 작성시 -> 개발자가 스코프 예상 작성하지만 런타임시 에러 발생가능 
 * 
 * 호이스팅?
 * - var로 선언한 변수가 초기화가 제대로 되어 있지 않을떄 undefined로 최상단에 끌어올려지는 현상
 * - 런타임시 선언이 최상단으로 끌어 올려짐 
 *   문) 코드 작성시 예측하지 못한 실행결과 발생 
 *   추천) var를 사용x , let || const 사용, 함수 표현식 사용
 */

// case 💩
var global = 1;

function outer() {
    var global;
    console.log(global); // undefined, 선언과 할당 분리된 상황 == 호이스팅
    var global = 5;

    function inner() {
        var global = 10;

        console.log(global); // 10
    }

    inner(); 

    global = 1;

    console.log(global); // 1
}

outer();


// case 💩
function duplicatedVar() {
    var a;

    console.log(a); // undefined

    var a = 100;

    console.log(a); // 100
}

duplicatedVar();



// case 💩. 함수도 호이스팅 된다.
var sum; // 변수선언만 하고 초기화(할당) 하지 않는 경우
var sum2;

console.log(typeof sum);
console.log(sum()); // 3이 출력되네.. (호이스팅됨)
//console.log(sum2()); // sum2 is not a function

function sum() {     // 1. 해당 구문으로 선언하면 위로 올라가서 위에 호출되네(호이스팅)
    return 1+2;
}

sum2 = function() {   //2. 이거는 올라가지 않음.. 다음에 호출 가능 
    return 3+4;
}

console.log(sum2()); // 7 출력 


// ✨recommand. 아래와 같이 const로 함수 선언하는 방식으로 하기(호이스팅 x)

// console.log(fixedSum()) // error :  Cannot access 'fixedSum' before initialization 

const fixedSum = function() { // 함수 표현식* : 익명함수(우측) 작성해서 변수(좌측)에 할당
    return 7 + 7;
}

console.log(fixedSum()); // 14 정상 출력