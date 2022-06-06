/**
 * 단축평가 (short-circuit evaluation)
 */

/**
 * AND
 */
                  👇
true && true && '도달 O' 
         👇
true && false && '도달 X'

/**
 * OR
 */                   👇
false || false || '도달 O'

👇
true || true || '도달 X'

// ex1. 단축평가
// truthy , OR 연산자의 단축평가 사용된 에시
function fetchData() {
/** 이렇게 할 필요 없이 아래와 같이 
 *  if (state.data) {
 *      return state.data;
 *  } else {
 *      return 'Fetching...';
 *  }
 */
    return StaticRange.data || 'Fetching...';
}

// ex2. 단축평가
function favoriteDog(someDog) {
    // let favoriteDog;
    // if(favoriteDog) {
    //     favoriteDog = dog;
    // } else {
    //     favoriteDog = '냐옹';
    // }

    return (someDog || '냐옹') + '입니다.';
}

favoriteDog(); // '냐옹 입니다.', 값이 안들어가면 undefined로 넘어가 falsy로 귀결됨
favoriteDog('포메'); // '포메 입니다.'


// ex3. 단축평가 
const getActiveUserName(user, isLogin) {
    // if (isLogin) {
    //     if(user) {
    //         if(user.name) {
    //             return user.name;
    //         } else {
    //             return '이름 없음';
    //         }
    //     }
    // }

    if(isLogin && user) { //✨ 한 depth 줄이고, 단축평가 사용하여 명시적으로 간결해짐
        return user.name || '이름없음';
    }
}

