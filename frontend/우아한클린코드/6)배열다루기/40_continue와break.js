/**
 * Continue & Break 
 * 
 * 제어 하는 방법
 * 1. try catch에 throw 던지거나 
 * 2. 일반적인 for문 
 * 3. for of 문 
 * 4. for in 문
 * 
 * 참고.
 * https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/forEach
 * 
 * 예외를 던지지 않고는 forEach()를 중간에 멈출 수 없습니다. 중간에 멈춰야 한다면 forEach()가 적절한 방법이 아닐지도 모릅니다.

    다음 방법으로는 조기에 반복을 종료할 수 있습니다.

    간단한 for 반복문
    for...of, for...in 반복문
    Array.prototype.every()  //and 같은 
    Array.prototype.some()   //or 같은
    Array.prototype.find()
    Array.prototype.findIndex()
    다른 배열 메서드 every(), some(), find(), findIndex()는 배열 요소를 판별 함수에 전달하고, 그 결과의 참/거짓 여부에 따라 반복의 종료 여부를 결정합니다.
 */

const orders = ['first', 'second', 'third'];

// forEach, map, filter 전부다 continue랑 break 안됨 -- 배열 중간 흐름 제어 어려운 메소드
orders.forEach(function(order) {
    if(order === 'second') {
        //break; // 문법에러
    }

    console.log(order);
});

// 그냥 살펴만 보고 넘어감.😅