/**
 * 배열 요소에 접근하기 
 * element (요소 , 인덱스에 값 해당)
 * 
 * 인덱스로 배열 요소 접근하는 방법을 줄이면 좀 더 깔끔하게 보일 수 있다!✨🧐
 */

// case1
function operateTime(inputs, operators, is) {
    //inputs[0] 명시적이지 않음 
    inputs[0].split('').forEach((num) => {
        // some logic
    });


    inputs[1].split('').forEach((num) => {
        // some logic
    });
}

//개선. 넣을때부터 할당 되도록 (근데 동작 x)
function operateTime2(firstInput, secondInput, operators, is) {
    //inputs[0] 명시적이지 않음 
    firstInput.split('').forEach((num) => {
        // some logic
    });


    secondInput.split('').forEach((num) => {
        // some logic
    });
}

operateTime2([1], [2], 3, 4);


// case2. 
function clickGroupButton() {
    //💩
    //const confirmButton = document.getElementsByTagName('button')[0];
    //const cancelButton = document.getElementsByTagName('button')[1];
    //const resetButton = document.getElementsByTagName('button')[2];

    //✨ 명시적이게 리팩토링 
    const [confirmButton, cancelButton, resetButton] = document.getElementsByTagName('button');
    console.log([confirmButton]);
    console.log([cancelButton]);
    console.log([resetButton]);

    // .. some code
}


// case3. 개선전
// util 만들어서 개선 
function head(arr) {
    return arr[0] ?? ''; // 배열에 아무것도 없으면 undefined 출력됨
}

function formatDate(targetDate) {
    //const date = targetDate.toISOString().split('T')[0];
    //const [date] = targetDate.toISOString().split('T'); // 개선1. 배열 구조 할당으로 인덱스 지울 수 있다.
    const date = head(targetDate.toISOString().split('T')); //개선2. lodash 의 _.head() 참고

    const [year, month, day] = date.split('-'); // 배열 구조 할당

    return `${year}년 ${month}월 ${day}월`;
}

