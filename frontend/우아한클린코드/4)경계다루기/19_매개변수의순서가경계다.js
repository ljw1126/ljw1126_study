/**
 * 매개변수의 순서가 경게다 
 * 👉호출하는 함수 네이밍과 인자의 순서의 연관성을 고려하여 만듦
 * 
 * 1. 매개변수를 2개가 넘지 않도록 만든다. 
 * 2. 규칙적이지 않은 값이 들어오면 arguments, rest parameter 고려 
 * 3. 매개변수로 객체에 담아서 넘긴다.
 * 4. 이미 만든 하는 함수가 있다면 랩핑하는 함수 만듦 
 */

// 매개변수 순서에 따라 추론 가능 
function someFunc(someArg, someArg) {
    // .. some code
}

/*
genRandomNumber(1, 50);
getDates('2022-01-01', '2022-12-31');
getSuffleArray(1, 5);
*/

function someFunc(someArg1, someArg2, someArg3, someArg4) {
    // .. some code 
}

function getFunc(someArg1, someArg3) {
    someFunc(someArg1, undefined, someArg3); // 어쩔 수 없는 경우
}