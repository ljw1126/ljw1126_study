{
  /**
   * Print Loading State
   * 3. 로딩상태 표시 
   */
  type LoadingState = {
    state: 'loading';
  };

  type SuccessState = {
    state: 'success';
    response: {
      body: string;
    };
  };

  type FailState = {
    state: 'fail';
    reason: string;
  };

  type ResourceLoadState = LoadingState | SuccessState | FailState;

  function printLoginState( obj : ResourceLoadState):void{
    if( obj.state === 'loading' ){
      console.log('loading💩');
    }else if(obj.state === 'success'){
      console.log(`🎉🎉obj.response.body`);
    }else{
      console.log(`😂😂obj.reason`);
    }
  }


  printLoginState({ state: 'loading' }); // 👀 loading...
  printLoginState({ state: 'success', response: { body: 'loaded' } }); // 😃 loaded
  printLoginState({ state: 'fail', reason: 'no network' }); // 😱 no network
}
