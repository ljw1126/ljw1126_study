## DNS(Domain Naming System)
- 브라우저를 통해 www.google.com을 입력하게 될 경우 DNS 서버를 통해 도메인에 대한 IP 주소를 획득하고 라우터를 통해 서버로 가게 됨.
- 내용을 간략정리해서 기록함(211116)

<a href="https://medium.com/@maneesha.wijesinghe1/what-happens-when-you-type-an-url-in-the-browser-and-press-enter-bb0aa2449c1a">
What happens when you type an URL in the browser and press enter?
</a>


#### 도메인 주소로 IP 주소 획득하는 과정 
- 우선 도메인에 대한 IP 주소를 DNS 서버에 요청하기 앞서 DNS 캐싱이 있는지 확인하게 됨 
  - 1.브라우저 캐시
  - 2.사용자 OS 캐시
  - 3.router 캐시
  - 4.ISP(Internet Service Provider) 캐시 
- 캐싱을 하는 이유는 간단하게 생각하면, **네트워크 트래픽 조절, 데이터 전송시간↓, 서버 부하↓ 하기 위해서**
- 그래도 없을 경우 DNS 서버를 통해 IP주소를 찾게 됨 

<center>
    <img src="/images/cs/browser_work_dns_.png"/>
</center>

- 과정 
  -  처음에 DNS recursor(ISP의 DNS서버)가 root name server에 연락 함
  -  root name 서버는 *.com* 도메인 name server로 redirect함 
  -  *.com* name server는 *google.com* name server로 redirect함 
  -  *google.com* name server는 DNS 기록에서 www.google.com에 매칭되는 IP 주소를 찾고 DNS recursor로 보내게 됨 
  - 그리고 요청자에게 찾은 IP주소 return 

---

#### 참고. 홈페이지(Domain) 주소 구입 
<center>

[![노마드 코더 - 왜 도메인을 돈 주고 사야해? 도메인 시스템 6분 설명!](http://img.youtube.com/vi/ao0VtQnBBXA/0.jpg)](https://youtu.be/ao0VtQnBBXA) 

</center>

[요약]
- GoDaddy , 한국은 가비아, 후이즈(whois), 카페 24 같은 사이트를 통해 우리는 도메인 구매하게 됨 
- 위와 같은 사이트를 **"도메인 리셀러(중계상인)"** 이라고 생각하면 됨 
  -  도메인 리셀러들은 유저관리, 결제프로세스, 도메인 관리 등을 처리함 
  -  이에 따라 우리는 도메인 구매, 유저관리, 결제프로세스, 도메인 관리 등에 대한 비용 지불
  -  해당 비용에서 일부는 registry 회사에 보내지게 됨 
- **registry 란?**
  - IP와 도메인명을 연결해 놓은 전화번호부 라고 생각하면 됨 
  - 대표적으로 .com을 관리하는 verisign(미국 기업), .kr은 KISA(한국인터넷진흥원)
    - ICANN(국제인터넷주소관리기구) 비영리 기구가 있고 각 도메인에 대한 관리를 기업 또는 국가 기관에 위임
    - 새로운 registry(.com, .net같은) 등록하기 위해서는 ICANN에 2억 이상의 신청비용과 기술/인프라/보안 등에 대한 증명 해야함 
 
<center>
    <img src="/images/cs/dns_도메인이름_등록과정.png"/>
</center>



#### 참고. 내 컴퓨터에 연결되어 있는 DNS 확인하기
- 보통 ISP 사업자가 구축해놓은 인터넷 인프라를 이용하고 있기때문에 통신사 DNS 서버에 default로 연결되어 있음 
- (공유기사용,tp-link)cmd창을 열어서 nslookup 입력시 아래 결과 출력됨
    - **UnKnown이 출력되는 이유는 공유기 통해 사설IP를 할당받아 사용중이기 때문**
        
<img src="/images/cs/수동dns설정하지않는경우.png"/>

- tp-link 관리자 페이지(http://192.168.0.1)에 접속해서 확인해보면 연결된 DNS 서버 IP 주소를 확인할 수 있다.

<img src="/images/cs/tplink.png" alt="SKT DNS 서버 주소"/>

###### 국내 ISP 사업자(통신사) 와 구글에 대한 DNS 서버 주소 

|구분|기본DNS서버|서브DNS서버|
|:---|:---------:|:---------:|
|SKT|**219.250.36.130**|**210.220.163.82**|
|KT|168.126.63.1|168.126.63.2|
|LG|164.124.101.2|203.248.252.2|
|구글(Public)|8.8.8.8|8.8.4.4|

- SKT를 사용하고 있기때문에 **해당 DNS 서버를 IP주소**를 획득하고 있었음 
- 그럼 DNS 서버를 수동으로 변경해보자.

###### 구글 DNS 서버 변경 후 확인해보기
- window + R  
<img src="/images/cs/dns%20설정%20변경1.png"/>
<br/>
<img src="/images/cs/dns%20설정%20변경2.png"/>
<br/>
<img src="/images/cs/dns%20설정%20변경3.png"/>
<br/>
- 다시 cmd 창 통해 nslookup 명령어 입력시 DNS 변경 된것을 확인가능 
<img src="/images/cs/dns%20설정%20변경4%20결과.png"/>

###### tracert 명령어
- tracert 명령어를 통해 해당 사이트 접속하기 위해 거쳐가는 router를 확인가능 
- 이외에 서버와 연결되는지 확인하기 위해 **telnet, ping, tcping**과 같은 명령어있음 

```
> tracert www.google.com
```  
<img src="/images/cs/tracert구글닷컴.png"/>

```
> tracert www.naver.com 
```  
<img src="/images/cs/tracert네이버.png"/>


#### 참고 기술 블로그
[https://aws.amazon.com/ko/route53/what-is-dns/](https://aws.amazon.com/ko/route53/what-is-dns/ 'AWS - DNS 란')
[https://it-mesung.tistory.com/180](https://it-mesung.tistory.com/180 'DNS 과정')
[https://devjin-blog.com/what-happen-browser-search/](https://devjin-blog.com/what-happen-browser-search/ '[번역] Browser에 www.google.com을 검색하면 어떤 일이 일어날까?')
[https://medium.com/@maneesha.wijesinghe1/what-happens-when-you-type-an-url-in-the-browser-and-press-enter-bb0aa2449c1a](https://medium.com/@maneesha.wijesinghe1/what-happens-when-you-type-an-url-in-the-browser-and-press-enter-bb0aa2449c1a 'What happens when you type a URL in the browser and press enter?')
[https://velog.io/@pu1etproof/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%8A%A4%ED%84%B0%EB%94%94-2%EC%A3%BC%EC%B0%A8-DNS-%EC%84%9C%EB%B2%84](https://velog.io/@pu1etproof/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%8A%A4%ED%84%B0%EB%94%94-2%EC%A3%BC%EC%B0%A8-DNS-%EC%84%9C%EB%B2%84)