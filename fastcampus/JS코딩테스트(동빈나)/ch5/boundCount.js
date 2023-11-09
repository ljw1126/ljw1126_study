// 정렬된 순서를 유지하면서 배열에 삽일할 가장 왼쪽 인덱스 반환
function lowerBound(arr, target, left, right) {
    while(left < right) {
        const mid = parseInt((left + right) / 2);
        if(arr[mid] >= target) right = mid;
        else left = mid + 1;
    }
    return right;
}

// 정렬된 순서를 유지하면서 배열에 삽입할 가장 오른쪽 인덱스 반환
function upperBound(arr, target, left, right) {
    while(left < right) {
        const mid = parseInt((left + right) / 2);
        if(arr[mid] > target) right = mid;
        else left = mid + 1; // 최대한 오른쪽으로 이동하기
    }
    return right;
}

/* 
    값이 leftValue ~ rightValue 사이의 데이터의 개수를 반환하는 함수
    유의 : lowerBound 와 upperBound 의 end 변수의 값을 배열의 길이로 설정
 */
function countByRange(arr, leftValue, rightValue) {
    let rightIndex = upperBound(arr, rightValue, 0, arr.length);
    let leftIndex = lowerBound(arr, leftValue, 0, arr.length);

    console.log(`left : ${leftIndex}, right : ${rightIndex}`);
    return rightIndex - leftIndex;
}

const arr = [1, 2, 3, 3, 3, 3, 4, 4, 8, 9];
console.log(countByRange(arr, 4, 4)); // 값이 4인 데이터의 개수 출력 : 2개
console.log(countByRange(arr, -1, 3)); // 값이 [-1, 3] 범위에 있는 데이터 개수 출력, 결과가 6인데..
console.log(countByRange(arr, 3,3)); // 값이 3인 데이터의 개수 : 4개