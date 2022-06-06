/**
 * Array.length 
 * 배열의 마지막 인덱스에 가까움
 * length는 배열의 길이를 보장하지 못함💣
 * 
 * 잘못 쓰면 위험한 아이
 */

const arr = [1, 2, 3];

console.log(arr.length); // 3

arr.length = 10;

console.log(arr); // [ 1, 2, 3, , , , , , ,  ]💣

// 초기화 하는 것 만으로 내용이 날라감 ! (주의필요🧐)
Array.prototype.clear = function() {
    this.length = 0;
}

function clearArray(array) {
    array.length = 0;

    return array;
}

const arr = [1, 2, 3];
console.log(clearArray(arr)); // []
