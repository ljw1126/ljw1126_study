## Https

#### HTTP(HyperText Transfer Protocol)
인터넷 상에서 클라이언트와 서버가 자원을 주고 받을 때 쓰는 통신 규약 

#### HTTPS(HyperText Transfer Protocol Secure)
인터넷 상에서 정보를 암호화하는 SSL 프로토콜을 사용해 클라이언트와 서버가 자원을 주고 받을 때 쓰는 통신 규약 
HTTPS는 텍스트를 공개키 암호화 방식으로 암호화 함

- 고로 Https 를 사용한다는 것은 두가지 의미함 
  - **신뢰있는 사이트 보증** 
  - **서버와 클라이언트 간에 정보 교환시 암호화 통해 제 3자 노출 방지** 


#### 대칭키(Symmetric Key) vs 비대칭키(=공개키, public key) 방식

|        |대칭키<br/>(개인키,비밀키)|비대칭키<br/>(공개키)|
|:------:|:----------------------:|:----------------------:|
| 방식   | 송신key == 수신 key     | 송신 key ≠ 수신 key     |
| 장점   | 암호화, 복호화 시간 짦음 | key 관리 용이(N=2n), key 배포 문제 x |
| 단점   |- key 보안문제, 분배문제 o <br/>- 사용자 ↑ , 키관리 어려움↑ (N = n(n-1)/2)|암호화, 복호화 비용 증가|
| 활용   |기업/개인간 평문 암호화<br/> DES,SEED,AES알고리즘|전자서명,금융거래등 불특정 다수를 위한 서비스<br/>RSA 알고리즘|


#### 용어 참 헷갈린다.
- **비밀키(대칭키,개인키)** 의 경우 서버와 클라이언트가 동일한 키를 사용하기 때문에 처리 속도가 빠르나, 키에 노출로 인한 보안 문제와 키 배포 및 관리가 어렵다는 단점 있음
- 그래서 나온 **공개키(비대칭키)** 의 경우 공개키로 암호화하고 서버에서 개인키로 복호화 하기때문에 보안성은 높으나 키를 두개 사용하여 알고리즘이 복잡하여 속도가 느림 
- 그리고 **HTTPS의 경우 두 방식을 혼합해서 사용함**


<center>
    <img src="/images/cs/https인증1.png"/>
</center>


<br/>

※ 정상적으로 인증서 검증이 되지 않는 경우 브라우저에 **"신뢰할 수 없는 사이트 표시"** 출력됨

<br/>

<center>
    <img src="/images/cs/https인증2.png"/>
</center>

<br/>

**∴ 서버 인증은 비대칭키 , 서버와 클라이언트간의 데이터 교환은 대칭키 방식으로 함.**

<br/>

###### 유튜브 참고 

<center>

[![얄팍한 코딩사전 - HTTPS가 뭐고 왜 쓰나요? (Feat. 대칭키 vs. 비대칭키)](http://img.youtube.com/vi/H6lpFRpyl14/0.jpg)](https://www.youtube.com/watch?v=H6lpFRpyl14) 

</center>

###### 참고 블로그
[https://sdesigner.tistory.com/85](https://sdesigner.tistory.com/85)
[https://wayhome25.github.io/cs/2018/03/11/ssl-https/](https://wayhome25.github.io/cs/2018/03/11/ssl-https/ '초보몽키의 개발공부로그 - HTTPS와 SSL 인증서, SSL 동작방법')
