###### 참고 
[https://evan-moon.github.io/2019/06/22/my-home-to-google/](https://evan-moon.github.io/2019/06/22/my-home-to-google/ '우리집에서 구글까지 가는 길')
[https://galid1.tistory.com/53](https://galid1.tistory.com/53 'DNS 프로토콜')
[https://www.youtube.com/watch?v=BEK354TRgZ8](https://www.youtube.com/watch?v=BEK354TRgZ8 '테크톡 TCP/IP')
[https://tecoble.techcourse.co.kr/post/2021-10-02-tcp-ip/](https://tecoble.techcourse.co.kr/post/2021-10-02-tcp-ip/ '우아한 형제들 테크코스')

## TCP/IP 에 대해 
```

* 인터넷(Internet)이란?
 - 전 세계에 걸쳐 파일 전송 등의 데이터 통신 서비스를 받을 수 있는 컴퓨터 네트워크의 시스템 
 - 사업자가 만들어 놓은 인프라에 대한 사용료를 제공하고 모뎀을 통해 디지털 데이터 변복조가 이뤄지며 네트워크 통신 이뤄짐 
 - 그리고 네트워크 통신을 위해 미리 정해 놓은 공통된 매뉴얼을 프로토콜(protocol) 이라 함 

* TCP/IP란 
 - 인터넷에서 컴퓨터들이 서로 정보를 주고 받는데 쓰이는 프로토콜의 집합 
 - 계층 구조 
   Application Layer
  Transport Layer
   Internet Layer
   Network Access Layer 
   - 1. Application Layer
      - 특정 서비스를 제공하기 위해 애플리케이션 끼리 정보를 주고 받을 수 있음 
      - FTP, HTTP, SSH, Telnet, DNS, SMTP 
   - 2. Transport Layer 
      - 송신된 데이터를 수신측 애플리케이션에 확실히 전달하게 하는 역할 
      - TCP, UDP, RTP, RTCP 
   - 3. Internet Layer
      - 수신 측까지 데이터를 전달하기 위해 사용됨 
      - IP, ARP, ICMP, RARP, OSPF 
   - 4.  Network Access Layer 
      - 네트워크에 직접 연결된 기기간 전송을 할 수 있도록 함 
      - 이더넷, PPP, 토큰링 

※ 웹브라우저에 "www.google.com"을 입력하면 무슨일이 일어날까?
    - 웹브라우저에 주소 입력하면 구글 서버에 80포트로 HTTP request 메시지를 보내는 것 
    - 해당 요청을 인터넷을 통해 구글서버로 전달하기 위해 계층별로 필요한 정보를 패킷에 담아야 함 



  Application Layer (HTTP프로토콜)
   - http request가 들어감 
  Transport Layer (TCP 프로토콜)
   - SP, DP
   - SP : 내 컴퓨터에 포트 
   - DP : 80포트 (well-known 포트)
  Internet Layer (IP프토코톨)
   - 시작IP , 목적지 IP 
   - DNS 프로토콜 통해 도메인 정보로 IP주소를 알아냄 
     [절차]
     - 브라우저에서 OS에게 domain에 대한 ip 주소 요청
     - OS에서 DNS 서버로 요청을 보냄 ( 내 컴퓨터 확인해보기, DNS 서버는 통신사별로 다름, DNS 프로토콜 또한 application layer에 소속되어 있고 53포트 사용) 
	- 도메인이 담길 쿼리를 도메인 서버에 보내면 도메인 서버에서 ip 주소를 응답해줌 
            - DNS 는 전송계층의 UDP 프로토콜 사용 (포트 번호 말고 다른게 없음, 비연결지향형 프로토콜)         
  Network Access Layer  (Ethernet프로토콜)
  - MAC 주소 필요 
    - 우리의 목적지인 google 웹서버의 mac 주소가 필요한가?
  - 일단 물리적으로 연결된 우리집 공유기(게이트웨이)의 MAC 주소가 필요     ( 게이트 웨이 ip 확인 netstat -rn )
  - ARP 프로토콜 사용해서 (IP주소를 MAC주소로 바꾸어주는 주소해석 프로토콜) IP주소를 MAC주소로 변환

네트워크의 바다로 패킷이 나갈 준비가 됨 , 그런데 TCP는 연결지향형이기 때문에 절차가 남음 

==========================================

- 3way handshaking 
   - 송수신측이 연결되는 작업 
   - TCP header에 기록된 flag들(컨트롤 비트) 사용됨 
     URG/ACK/PSH/RST/SYN/FIN      --- 여기서는 SYN, ACK 플래그가 사용됨 
   - [흐름]
     1. client는 syn(=1)을 보냄
     2. server는 client 요청을 수락한다는 ack(=1)과 syn(=1) 플래그가 설정된 패킷을 보냄 
     3. client는 다시 ack(=1) 보냄으로써 연결 성립됨 
 
==========================================


※ 개인용 컴퓨터의 경우 private ip를 사용하기 때문에 공유기를 통해 나갈때 public ip 로 나감 
   이러한 작업은 NAT(network address translation)가 함


우리집 공유기를 나와 라우팅을 통해 구글가 연결된 게이트 웨이(라우터)까지 도착하면 
패킷의 ip 해더 통해 mac주소 구해와야함 
이때 이전에 설명했던 ARP 프로토콜을 사용해서 라우터가 연결된 네트워크로 브로드 캐스팅 됨 ( 목적지 구글 서버 ip 주소인분? )
목적지 구글서버가 자신의 ip로 온 ARP요청을 받고 mac주소를 응답해줌 
이제 목적지 구글 서버에 mac주소를 알았으니 물리적으로 데이터 전송 가능 

※ 이때 Internet Layer의 ip 정보와 network access layer에 mac주소 통해 서버에 도착했음 

전송계층에서 작성된 헤더 정보에 따라 80포트 프로세스(애플리케이션)에게  데이터를 전달하게 됨 

application layer까지 오면 웹서버가 사용될 http request를 얻어서 응답을 해줄 수 있음 


===============================================
http request 요청/응답 종료시 TCP의 컨트롤 비트가 사용됨 (ack, fin flag 사용됨 ) 
4-way-handshaking 통해 통신종료 절차 진행 
- client가 서버로 연결을 종료하겠다는 fin 플래그를 전송함 
- server가 client 한테 ack 플래그 보냄 
- client가 통신이 끝났다고 fin 보냄 
- server는 확인으로 ack 보냄

client측에서 서버로 부터 fin을 받았다고 바로 연결된 소켓을 닫아버리면 아직 도착 못한 패킷 정보 소실 될 수 있음 
그래서 client는 일정시간 동안 소켓을 닫지 않고 잉여패킷을 기다리는 time_wait 상태를 통해 받고 소켓 종료 

===============================================

tcp는 흐름제어, 오류제어, 혼잡제어 통해 신뢰성있는 네트워크 통신이 가능하도록 해줌 

```