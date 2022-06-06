/**
 * Early return 
 * 
 * 정리.
 * 수많은 Early return을 만드는 것도 좋지는 않다.
 * 하지만 수많은 로직이 하나에 로직 의존하고 있을때 
 * Early return 사용하면 명시적으로 간결하게 할 수 있다. 👨‍💻
 */

//case1. 
function loginServier(isLogin, user) {
    if (!isLogin) { // 1. 로그인 여부 확인
        if (!checkToken()) { // 2. 토큰 존재 여부 
            if (!user.nickName) { // 3. 기가입 유저 확인
                return registerUser(user);
            } else { // 가입 처리 or 토큰 갱신 후 결과 처리
                refreshToken();

                return '로그인 성공';
            }
        } else {
            throw new Error('No token');
        }
    }
}

//refactoring ✨ 사고가 편한(사람이 읽기 편한) 코드로 바뀜
function loginServiceRefactor(isLogin, user) {
    
    // Early Return ✨
    if (isLogin) return;

    if (!checkToken()) throw new Error('No token');

    if (!user.nickName) {
        return registerUser(user); // 회원가입
    } 

    login();
}

function login() {
    refreshToken();

    return '로그인 성공';
}
