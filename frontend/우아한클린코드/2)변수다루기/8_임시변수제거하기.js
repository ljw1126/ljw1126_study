/**
 * 8. 임시 변수 제거하기 
 * 
 * 임시변수 ?
 * 어느 특정 공간에 Scope 안에서 전역변수처럼 활용되는 친구
 * 함수를 잘게 쪼갠다면 임시변수가 문제 안되지만, 
 * 함수가 커진다면 다른 누군가가 임시변수 조작에 대한 유혹 받을 수 있음💩(문제 야기 가능)
 * 
 */

// case1. 
function getElements() {
    /*
    const result = {}; // 임시변수, CRUD 유혹 발생 가능 

    result.title = document.querySelector('.title');
    result.text = document.querySelector('.text');
    result.value = document.querySelector('.value');

    return result;
    */

    /*
    const result = {
        title : document.querySelector('.title'),
        text : document.querySelector('.text'),
        value : document.querySelector('.value')
    };

    return result;
    */

    // elements 가져올 때 위험 요소 있을 수 있지만, 임시변수 제거함으로써 간결해짐
    return {
        title : document.querySelector('.title'),
        text : document.querySelector('.text'),
        value : document.querySelector('.value')
    };
}


/**
 * case. 날짜 포맷 처리 함수 
 * 
 * if. 추가적인 스펙 발생할 경우 ! 
 *     - 방법1. 함수 추가 
 *     - 방법2. 이 함수 수정  👉 공용 함수 수정시 다른 곳에서 문제 발생가능 
 */
function getDateTime(targetDate) {
    let month = targetDate.getMonth();
    let day = targetDate.getDate();
    let hour = targetDate.Hours();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;

    return {
        month, day, hour
    }
}

/**
 * then. 
 * 1. 바로 return 하도록 수정 
 */
 function getDateTimeFixed(targetDate) {
    let month = targetDate.getMonth();
    let day = targetDate.getDate();
    let hour = targetDate.Hours();

    return {
        month : month >= 10 ? month : '0' + month,
        day : day >= 10 ? day : '0' + day,
        hour : hour >= 10 ? hour : '0' + hour
    }
}

function getDateTime() {
    const currentDateTime = getDateTimeFixed(new Date()); // 함수 추상화 한 것을 재활용 가능
    
    // 로직 ok
    
    return {
        month : func(currentDateTime.month) + '분 전',
        day : currentDateTime.month + '분 전',
        hour : currentDateTime.month + '분 전'
    }

}

/**
 * 임시변수를 사용하지 않는 방법을 선호하라! 
 * 이유 -> 명령형으로 가득한 로직 생성
 *      -> 어디서 어떻게? 디버깅 힘든  // 중간 로직이 들어가니..
 *      -> 추가적인 코드를 작성 유혹을 많듬 
 * 
 * 해결책 -> 함수나누기 
 *        -> 바로 반환 
 *        -> 고차 함수 사용(map, filter, reduce)
 *        -> 선언형** 사용 
 */
