## TCP , UDP 비교 
|  구분  |  TCP   |   UDP|
|:------:|:------:|:------:|
|서비스| 연결 지향형 서비스| 비연결 지향형 서비스|
|통신 방식|port 이용 | 소켓주소 이용<br/> (ip주소 + port 번호)|
|세션(연결) 설정|3-way handshaking(논리적연결)<br/>4-way handshaking(연결해제)|x|
|신뢰성 확보|흐름제어/오류제어/혼잡제어|x|
|전송속도|느림|빠름|
|장점|신뢰성있는 정보전송가능|실시간 통신 적합<br/>(비연결형에 제어가 없으므로)|
|단점|절차로 인한 지연 발생|신뢰성 보장x|
|용도|http, SMTP|VoIP|


## TCP의 흐름제어, 오류제어, 혼잡제어
- 흐름제어 
  - 송/수신측 사이의 데이터 처리 속도 차이(흐름)을 제어하기 위한 기법으로 데이터 처리 속도를 조절하여 수신자의 버퍼 오버플로우를 방지. 
  - Stop and Wait / Sliding Window 
- 오류제어  
  - 오류 검출과 재전송 (ARQ,Automatic Repeat Request) 
  - Stop and Wait ARQ / Go-Back-n ARQ / SR(Selective-Reject) ARQ
- 혼잡제어 
  -  네트워크의 혼잡을 피하기 위해 송신측에서 보내는 데이터의 전송 속도를 제어 
  -  AIMD(Additive Increase Multicative Decrease , 1배씩 증가 2배 감소) / Slow Start(2배씩 지수함수적 증가, 문제발생시 window size 1로 감소)


## 참고 
[https://youtu.be/ikDVGYp5dhg](https://youtu.be/ikDVGYp5dhg '우아한 테크톡')
[https://velog.io/@jsj3282/TCP-%ED%9D%90%EB%A6%84%EC%A0%9C%EC%96%B4%ED%98%BC%EC%9E%A1%EC%A0%9C%EC%96%B4-%EC%98%A4%EB%A5%98%EC%A0%9C%EC%96%B4](https://velog.io/@jsj3282/TCP-%ED%9D%90%EB%A6%84%EC%A0%9C%EC%96%B4%ED%98%BC%EC%9E%A1%EC%A0%9C%EC%96%B4-%EC%98%A4%EB%A5%98%EC%A0%9C%EC%96%B4 'TCP 흐름제어, 오류제어, 혼잡제어')
