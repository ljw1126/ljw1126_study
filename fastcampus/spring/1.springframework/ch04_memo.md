## ch04. 예외처리
- 함수마다 try, catch로 예외처리 했던 방식에서 @ExceptionHandler 로 변환해옴 
- TIP.실무에서 보통 API호출시 실패에 대한 공통 DTO를 생성해서 응답을 보내도롬 한다.
- 로직에서 문제 발생시 에러 결과는 넘기는 구조는 코드 읽기 불편, 로직 복잡도 상승, 유지보수 어려움, 재활용성이 낮아지므로 피하기 어려움
- 또한 예외 처리 구조를 다 가지고 있는건 좋지 않으니 @ExceptionHandler로 처리하는 방식을 배움 
```java
    // DMakerController.java 에서 비지니스 로직시 DMakerException 발생하는거는 아래 메소드에서 처리함. (service단에서 에러 발생하면 여기로 옴)
    // @ResponseStatus(value = HttpStatus.CONFLICT) 추가하여 응답에 400번대 코드를 지정할 수 있지만, 에러코드가 어색할 수 있으니 , 상태코드는 200 내려주고 내용에 부가적으로 코드를 넣어주거나 한다함
    @ResponseStatus(value = HttpStatus.CONFLICT) 
    @ExceptionHandler(DMakerException.class)     
    public DMakerErrorResponse handleException(
            DMakerException e,
            HttpServletRequest request){
        log.error("errorCode : {} , url : {}, message:{}"
                    , e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage());

        return DMakerErrorResponse.builder()
                .errodrCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }


```

#### Application단위의 ExceptionHandler 클래스 
- 실제 업무에서 Controller가 여러개 생성되기 때문에 매번 @ExceptionHandler를 Controller마다 생성하는건 좋지 x 
- 그래서 Global Exception Handler용 class를 생성함 
- exception 패키지에 DMakerExceptionHandler.class 생성 후 **@RestControllerAdvice** 추가함 
- ExceptionHandler를 사용하지 않을 경우 에러메시지를 정확히 핸들링 할 수 없고, 서버에서 보내는 내용만 받기 때문에 상대측에서 처리 까다로움
- 그래서 정의된 에러 메시지를 보냄 으로써 상대측 이해도를 높일 수 있음(장점1)
- 또한 불필요하게 자세한 에러 로그가 노출이되면 보안 상으로도 좋지 않으므로 필요한 정보만 보여줄 수 있음 (장점2)  
  - 구현시 **handlerBadRequest() 에서 매개변수를 Exception e 로 주지 않아 인식을 못함!**
  - 수정하니 둘다 정상적으로 동작함 ! 
```java 

@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {

    // Controller 상에 발생하는 DMakerException 은 아래 함수 통해 처리해줌

    @ExceptionHandler(DMakerException.class)
    @ResponseBody
    public DMakerErrorResponse handleException(
            DMakerException e,
            HttpServletRequest request){
        log.error("errorCode : {} , url. : {}, message:{}"
                , e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage());

        return DMakerErrorResponse.builder()
                .errodrCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class
            ,MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public DMakerErrorResponse handlerBadRequest(
            Exception e,
            HttpServletRequest request){ // 매개변수가 DMakerException일때 동작안함, Exception으로 수정하니 아래꺼도 정상동작함
        log.error(" url : {}, message:{}"
                , request.getRequestURI(), e.getMessage(),  e);

        return DMakerErrorResponse.builder()
                .errodrCode(DMakerErrorCode.INVALID_REQUEST)
                .errorMessage(DMakerErrorCode.INVALID_REQUEST.getMessage())
                .build();
    }

    // 최후의 보루 : 어떤 에러가 발생할지 모를 경우 (라이브러리 에러 발생해도)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DMakerErrorResponse handlerException(
            Exception e,
            HttpServletRequest request){
        log.error(" url. : {}, message:{}"
                , request.getRequestURI(), e.getMessage(), e);

        return DMakerErrorResponse.builder()
                .errodrCode(DMakerErrorCode.INTERNAL_SERVCE_ERROR)
                .errorMessage(DMakerErrorCode.INTERNAL_SERVCE_ERROR.getMessage())
                .build();
    }

}

```

###### @RestControllerAdvice , @ControllerAdvice 찾아보기