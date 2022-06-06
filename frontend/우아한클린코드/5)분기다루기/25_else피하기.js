/**
 * else 피하기 
 * 
 * 프로그래밍의 흐름이 끊길 수 있는게, 
 * 스타일상의 문제뿐만 아니라 
 */

// ex
function getActiveUserName(user) {
    // 의미없는 coverage가 넓음..
    // if(user.name) {
    //     return user.name;
    // } else {
    //     return '이름없음';
    // }

    // better 
    /**
     * if(user.name) {
     *      return user.name;
     * }
     * 
     * return '이름없음';
     */

    // best
    return user.name || '이름없음'; // 단축평가 하거나 
}

// case 💩
/**
 * age 가 20미만 시 특수 함수 실행
 * > 함수가 두개의 역할을 하고 있음
 */
function getHelloCustomer(user) {
    // if (user.age < 20) {
    //     // exc some func
    // } else { // 쓸데없이 습관적으로 하게 됨.
    //     return 'hello';
    // }

    // better than
    if (user.age < 20) {
        // some func
    }

    return 'hello';
}
