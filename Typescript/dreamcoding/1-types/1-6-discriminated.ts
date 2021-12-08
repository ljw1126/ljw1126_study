{
  /*
  * discriminated union 차별된 유니온 
  * 활용도 높음 , 직관적인 코드 작성가능해짐 
  * union 타입을 사용시 어떤 케이스든 공통 속성을 가지고 있으면 직관성 높이면서 이용가능해짐 
  */

  // function: login -> success, fail ⏱
  type SuccessState = {
    result: 'success';
    response: {
      body: string;
    };
  };
  type FailState = {
    result: 'fail';
    reason: string;
  };
  type LoginState = SuccessState | FailState;

  function login(): LoginState {
    return {
      result: 'success',
      response: {
        body: 'logged in!',
      },
    };
  }

  // printLoginState(state: LoginState)
  // success -> 🎉 body
  // fail -> 😭 reason
  function printLoginState(state: LoginState) {
    if (state.result === 'success') {
      console.log(`🎉 ${state.response.body}`);
    } else {
      console.log(`😭 ${state.reason}`);
    }
  }
}
