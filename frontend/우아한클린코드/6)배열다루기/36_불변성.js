/**
 * 불변성 (immutable)
 * 
 * 1. 배열을 복사한다. copy array , array clone 
 * 2. 새로운 배열을 반환하는 메서드들을 활용한다.
 * 
 * Array.prototype.* 함수 중에 
 * map, filter, slice 등이 원본 배열을 복사해서 결과 반환하는 형태✨
 */
const origin = ['123', '456', '789'];

const newArray = origin;  // [..origin] 으로 하면 새로운 배열 생성 

origin.push(10);
origin.push(11);
origin.push(12);
origin.unshift(0);

console.log(origin); //[ 0, '123', '456', '789', 10, 11, 12 ]
console.log(newArray); //[ 0, '123', '456', '789', 10, 11, 12 ]
