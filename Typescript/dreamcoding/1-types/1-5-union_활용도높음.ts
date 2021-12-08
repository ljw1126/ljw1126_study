{
  /**
   *  Union Types: OR 로 이해하면 충분 ! 
   *  - 발생가능한 케이스중에 하나만 선택하고 싶을때 
   *  - typescript에서 활용도가 높은 타입 !
   */
  type Direction = 'left' | 'right' | 'up' | 'down';
  function move(direction: Direction) {
    console.log(direction);
  }
  move('down'); // Direction 정의된 text 인자 아니면 할당 불가함 ! , 

  type TileSize = 8 | 16 | 32;
  const tile: TileSize = 16;

  // function: login -> success, fail ⏱
  type SuccessState = {
    response: {
      body: string;
    };
  };
  type FailState = {
    reason: string;
  };
  type LoginState = SuccessState | FailState;

  function login1(): LoginState {   // 보통 Promise<LoginState>인데 예시는 간단히 
    return {
      response: {
        body: 'logged in!',
      },
    };
  }

  // printLoginState(state: LoginState)
  // success -> 🎉 body
  // fail -> 😭 reason
  function printLoginState1(state: LoginState) {
    if ('response' in state) {
      console.log(`🎉 ${state.response.body}`);
    } else {
      console.log(`😭 ${state.reason}`);
    }
  }

  // function test2(state:LoginState){
  //   if('reason' in state){
  //     console.log(`💩${state.reason}`);
  //   }else{
  //     console.log(`✨${state.response.body}`);
  //   }
  // }

}
 
