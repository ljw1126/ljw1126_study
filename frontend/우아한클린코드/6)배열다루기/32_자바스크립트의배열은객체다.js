/**
 * javascript 배열은 객체다
 */

const arr = [1, 2, 3];

arr[3] = 'test';
arr['property'] = 'string value';
arr['obj'] = {};
arr['{}'] = [1, 2, 3];
arr['func'] = function() {
    return 'hello'
}

for(let i = 0 ; i < arr.length ; i++) {
    console.log(arr[i]); // 1, 2, 3, test
}

console.log(arr.func()); // hello

// 위와 동일하다 (배열도 객체다)
const obj = {
    arr : [1, 2, 3],
    3: 'test',
    property : 'string value',
    obj: {},
    '{}' : [1, 2, 3],
    func : function() {
        return 'hello'
    }
}

// Array 검사는 Array.isArray() 활용하기👍
// length 는 String도 가지고 있으니 그걸로 판별 💩
const arr = '[1, 2, 3]';

console.log(Array.isArray(arr)); // false 
