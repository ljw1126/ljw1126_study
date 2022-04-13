> 회원 가입 후 ec2 생성할때 키페어 생성하는데, 인스턴스 생성시 한번만 생성되는 거라 잘 보관할 것 ! ex. keyPair.pem 

## 클라우드 서비스란 ?
> 물리적 자원 혹은 논리적 자원을 대여하는 것

## EC2 란?
> EC2는 Elastic Compute Cloud 이다.


## 인스턴스 접속 
```
> chmod 400 keyPair.pem     // 권한 수정
> ssh -i keyPair.pem ec2-user@Ipv4주소       // 인스턴스 접속, 우분투는 ubuntu@

# Node.js 설치 예제 -- nvm 활용 
> curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash     //공식 https://github.com/nvm-sh/nvm
> export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm

> nvm       // 명령어 확인 
> nvm install --lts    // 안정적인 버전 설치 
> nvm user --lts       // npm도 같이 깔림 
> node -v
> npm -v

# express 패키지 설치 
> mkdir app 
> cd app 
> npm i -S express     //express 패키지 설치 
> ls 
> vi index.js // 작성 후 저장

  const express = requires('express')
  const app = express() 

  app.get('', async(req,res) => {
      res.send('Hello World\n');
  });

  app.listen(3000, () => {
      console.log('App is listening 3000 port...');
  })  

> node index.js     // 실행
> curl http://localhost:3000    
  Hello world //출력

# 3000 port open 
- 보안 그룹 > 인바운드 > 편집 눌러서 규칙(port, 접속 ip 등) 추가함   
- 기본적으로 인스턴스는 22포트만 허용함
```

## 인스턴스 삭제 
- 인스턴스 종료 
- 보안 그룹 삭제 (default 제외)
- ELASTIC BLOCK STORE > 볼륨 확인  
- 네트워크 및 보안 > 키 페어 
  - 키 삭제 