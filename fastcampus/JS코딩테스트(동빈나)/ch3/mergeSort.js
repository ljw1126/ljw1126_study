/*
function merge(arr, left, mid, right) {
    let i = left;
    let j = mid + 1;
    let k = left; // 정렬 결과 담기는 임시 배열용 인덱스 
    
    const sorted = [];

    while(i <= mid && j <= right) {
        if(arr[i] <= arr[j]) sorted[k++] = arr[i++];
        else sorted[k++] = arr[j++];
    }

    // 남은 항목 처리 
    if(i > mid) {
        for(; j <= right; j++) sorted[k++] = arr[j];
    } else {
        for(; i <= mid; i++) sorted[k++] = arr[i];
    }

    // 정렬된 배열 결과를 원본 배열에 반영하기 
    for(let x = left; x <= right; x++) {
        arr[x] = sorted[x];
    }
}

function mergeSort(arr, left, right) {
    if(left === right) return;

    let mid = parseInt((left + right) / 2);
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
}
*/

function merge(arr, left, mid, right) {
    let i = left;
    let j = mid + 1;
    let k = left; // *여기 틀림
    let sorted = [];
    
    while(i <= mid && j <= right) {
      if(arr[i] <= arr[j]) sorted[k++] = arr[i++];
      else sorted[k++] = arr[j++];
    }
    
    if(i > mid) {
      for(; j <= right; j++) sorted[k++] = arr[j];
    } else {
      for(; i <= mid; i++) sorted[k++] = arr[i];
    }
    
    for(let x = left; x <= right; x++) {
      arr[x] = sorted[x];
    }
  }
  
  function mergeSort(arr, left, right) {
     if(left === right) return;
     
     const mid = parseInt((left + right) / 2);
     mergeSort(arr, left, mid);
     mergeSort(arr, mid + 1, right);
     merge(arr, left, mid, right);
  }


let arr = [29, 33, 41, 10, 62];
mergeSort(arr, 0, arr.length - 1);
console.log(arr);

let _arr = Array.from({ length : 100000 }, () => Math.floor(Math.random() * 1000));
const startTime = new Date().getTime();
mergeSort(_arr, 0, _arr.length - 1);
const endTime = new Date().getTime();

console.log(endTime - startTime, "ms");
console.log(_arr);