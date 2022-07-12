## (선택) zsh , oh-my-zsh 설치
```
$ echo $SHELL        // 현재 사용중이 shell 
$ echo $0 
$ cat /etc/shells    // 지원하는 로그인 shell 목록 
$ chsh -s /bin/zsh   // shell 변경

$ apt-get install -y zsh
$ apt-get install -y curl 

// oh-my-zsh 설치
# sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
Cloning Oh My Zsh...
Error: git is not installed

$ apt-get install -y git
$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
$ git --version 


```
## apache pig 공식 reference
https://pig.apache.org/docs/r0.16.0/start.html
https://pig.apache.org/docs/r0.16.0/basic.html
https://pig.apache.org/docs/r0.16.0/func.html

## pig turorial 사이트 
https://www.tutorialspoint.com/apache_pig/index.htm

## pig 설치 

#### 참고 
https://www.tutorialspoint.com/apache_pig/apache_pig_installation.htm
https://pig.apache.org/releases.html
https://dlcdn.apache.org/pig/


```
# 파일 두개 다운로드 후 압축 풀기 
wget https://dlcdn.apache.org/pig/pig-0.16.0/pig-0.16.0-src.tar.gz
wget https://dlcdn.apache.org/pig/pig-0.16.0/pig-0.16.0.tar.gz

// 왜 둘다 다운 받는거지 ?? pig-0.16.0.tar.gz 압축 풀고 작업함

vim ~/.bashrc 

  export PIG_HOME = /home/pig/pig-0.16.0
  export PATH=$PATH:$PIG_HOME/bin
  export PIG_CLASSPATH=$HADOOP_HOME/conf

source ~/.bashrc 
pig -version 
pig 

  grunt>          // ctrl + d 누르면 빠져 나옴


# *.pig 파일 생성해서 실행 테스트 
https://www.tutorialspoint.com/apache_pig/apache_pig_execution.htm

*.pig 스크립트 작성 후 local로 돌리면 local 테스트가 가능하다 
$ pig -x local sample.pig                  // 당연히 load 하는 파일도 서버에 있어야 겠지 
<->
$ pig -x mapreduce sample.pig              // 이 경우 hdfs 올려져 있는 파일 경로가 제공되야 함(10020 포트 왜 호출이 안되지??)
```

int, double, log, chararray, 


#### 테스트 파일 master docker에 올리자 !! (생략)

> 느낌이 .. () 객체, {} 배열,  {(), (), (), ..} 배열안에 객체 느낌인듯 // 배열이 group 으로, 객체가 relation으로 부르는듯 ?


※  튜플 데이터 u.item, 영화정보 => metadata
※  튜플 데이터 u.data, 영화에 대한 사용자 평점 정보 => ratings
    (사용자 Id, 영화 ID, 평점, 타임 스탬프),
    (사용자 Id, 영화 ID, 평점, 타임 스탬프),
    (사용자 Id, 영화 ID, 평점, 타임 스탬프),
    ...

## 테스트 #1. Create a relation named 'ratings'

> 파일경로 /home/sample_data/ml-100k/

**udemy1.pig**
```
# USING PigStorage() 가 있든 없든 default delimiter는 띄어쓰기 인듯
ratings = LOAD '/home/sample_data/ml-100k/u.data' AS (userID : int, movieID : int , rating : int, ratingTime : int);

DUMP ratings;
```

**실행**
> pig -x local udemy1.pig

**데이터 형식(u.data)** 
```
(생략..)
사용자id 영화id 평점      timestamp
716     204     5       879795543
276     1090    1       874795795
13      225     2       882399156
12      203     3       879959583
```

**결과**
```
(생략..)
(378,78,3,880056976)
(880,476,3,880175444)
(716,204,5,879795543)
(276,1090,1,874795795)
(13,225,2,882399156)
(12,203,3,879959583)
```

--- 


## 테스트 #2. Use PigStorage if you need a different delimiter.

**데이터 형식(u.item)**
```
(생략..)
9|Dead Man Walking (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(199~5)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0
10|Richard III (1995)|22-Jan-1996||http://us.imdb.com/M/title-exact?Richard%20III%20(1995)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|1|0
11|Seven (Se7en) (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Se7en%20(1995)|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|1|0|0
12|Usual Suspects, The (1995)|14-Aug-1995||http://us.imdb.com/M/title-exact?Usual%20Suspects%20The~%20(1995)|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|1|0|0
```

**udemy2.pig**
```
# videoRelease의 경우 데이터가 없음
metadata = LOAD '/home/sample_data/ml-100k/u.item' USING PigStorage('|')                            
            AS (movieID : int, movieTitle : chararray, releaseDate : chararray, wideoRelease : chararray,imdbLink : chararray);                                                                              │
                                                                                             │
DUMP metadata;
```

**실행결과**
```
(1677,Sweet Nothing (1995),20-Sep-1996,,http://us.imdb.com/M/title-exact?Sweet%20Nothing%20(1995))      
(1678,Mat' i syn (1997),06-Feb-1998,,http://us.imdb.com/M/title-exact?Mat%27+i+syn+(1997))              
(1679,B. Monkey (1998),06-Feb-1998,,http://us.imdb.com/M/title-exact?B%2E+Monkey+(1998))                
(1680,Sliding Doors (1998),01-Jan-1998,,http://us.imdb.com/Title?Sliding+Doors+(1998))
```

--- 

## 테스트 #3. Creating a relation from another relation;  

**데이터 형식(u.item)**
```
(생략..)
9|Dead Man Walking (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(199~5)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0
10|Richard III (1995)|22-Jan-1996||http://us.imdb.com/M/title-exact?Richard%20III%20(1995)|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|1|0
11|Seven (Se7en) (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Se7en%20(1995)|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|1|0|0
12|Usual Suspects, The (1995)|14-Aug-1995||http://us.imdb.com/M/title-exact?Usual%20Suspects%20The~%20(1995)|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|1|0|0
```

**udemy3.pig** 
딱 선언한 것만 뽑네
```
metadata = LOAD '/home/sample_data/ml-100k/u.item' USING PigStorage('|')
        AS (movieID : int, movieTitle : chararray, releaseDate : chararray, videoRelease : chararray, imdbLink : chararray);

nameLookup = FOREACH metadata GENERATE
                movieID,
                movieTitle,
                ToUnixTime(ToDate(releaseDate, 'dd-MMM-yyyy')) as releaseTime;

DUMP nameLookup;
```

> 1-Jan-1995 의 경우 dd-MMM-yyyy 포맷

#### ToDate() 
> This function is used to generate a DateTime* object according to the given parameters.
```
grunt> ToDate(milliseconds)

grunt> ToDate(iosstring)

grunt> ToDate(userstring, format)

grunt> ToDate(userstring, format, timezone)
```


**실행결과** 
```
(1676,War at Home, The (1996),820454400)         // 함수 결과 820454400
(1677,Sweet Nothing (1995),843177600)
(1678,Mat' i syn (1997),886723200)
(1679,B. Monkey (1998),886723200)
```


---

## 테스트 #4-1. Group By 

**데이터 형식(u.data)** 
```
(생략..)
사용자id 영화id 평점      timestamp
716     204     5       879795543
276     1090    1       874795795
13      225     2       882399156
12      203     3       879959583
```

**udemy4-1.pig** 
```
ratings = LOAD '/home/sample_data/ml-100k/u.data' AS (userID : int, movieID : int , rating : int, ratingTime : int);

ratingsByMovieID = GROUP ratings BY movieID;

DUMP ratingsByMovieID; 

-- DESCRIBE : 관계성 구조/형식/명세 (?) 출력
DESCRIBE ratings;        
DESCRIBE ratingsByMovieID;
```


**실행결과**
```
(생략..)
(1664,{(782,1664,4,891499699),(880,1664,4,892958799),(839,1664,1,875752902),(870,1664,4,890057322)})
(1672,{(828,1672,2,891037722),(896,1672,2,887159554)})
(1678,{(863,1678,1,889289570)})
(1679,{(863,1679,3,889289491)})
(1680,{(863,1680,2,889289570)})
(1681,{(896,1681,3,887160722)})
(1682,{(916,1682,3,880845755)})

ratings: {userID: int,movieID: int,rating: int,ratingTime: int}

ratingsByMovieID: {group: int,ratings: {(userID: int,movieID: int,rating: int,ratingTime: int)}}
```

## 테스트 #4-2. Group By & AVG() 

**데이터 형식(u.data) 및 중간과정** 
```
(생략..)
716     204     5       879795543
276     1090    1       874795795
13      225     2       882399156
12      203     3       879959583

# ratings (load)
(생략..)
(378,78,3,880056976)
(880,476,3,880175444)
(716,204,5,879795543)
(276,1090,1,874795795)
(13,225,2,882399156)
(12,203,3,879959583)

# ratingsByMovieID (group by)
(생략..)
(1664,{(782,1664,4,891499699),(880,1664,4,892958799),(839,1664,1,875752902),(870,1664,4,890057322)})
(1672,{(828,1672,2,891037722),(896,1672,2,887159554)})
(1678,{(863,1678,1,889289570)})
(1679,{(863,1679,3,889289491)})
(1680,{(863,1680,2,889289570)})
(1681,{(896,1681,3,887160722)})
(1682,{(916,1682,3,880845755)})
```

**udemy4-2.pig**
```
ratings = LOAD '/home/sample_data/ml-100k/u.data' AS (userID : int, movieID : int , rating : int, ratingTime : int);

ratingsByMovieID = GROUP ratings BY movieID;

avgRatings = FOREACH ratingsByMovieID GENERATE
                group AS movieID,
                AVG(ratings.rating) AS avgRating;

DUMP avgRatings;

DESCRIBE ratings;
DESCRIBE ratingsByMovieID;
DESCRIBE avgRatings;
```

**실행결과**
```
(생략..)
(1679,3.0)
(1680,2.0)
(1681,3.0)
(1682,3.0)

ratings: {userID: int,movieID: int,rating: int,ratingTime: int}

ratingsByMovieID: {group: int,ratings: {(userID: int,movieID: int,rating: int,ratingTime: int)}}

avgRatings: {movieID: int,avgRating: double}  -- AVG를 하게 되면 해당 그룹 :: {} 내에 모든 객체 :: () 를 알아서 구해주는게 아닌가 싶음
```


--- 


## 테스트 #5. 평균 평점이 4.0 초과만 필터링 

**udemy5.pig**
```
ratings = LOAD '/home/sample_data/ml-100k/u.data' AS (userID : int, movieID : int , rating : int, ratingTime : int);

ratingsByMovieID = GROUP ratings BY movieID;

avgRatings = FOREACH ratingsByMovieID GENERATE
                group AS movieID,
                AVG(ratings.rating) AS avgRating;

fiveStarMovies = FILTER avgRatings BY avgRating > 4.0;

DUMP fiveStarMovies;

DESCRIBE fiveStarMovies;
```


**실행결과**
```
영화Id, 평균 평점
(1639,4.333333333333333)        // avgRating > 4.0 이상만 뽑아냄
(1642,4.5)
(1653,5.0)

fiveStarMovies: {movieID: int,avgRating: double}
```


--- 

## 테스트 #6. 평균폄점 4.0 초과하는 영화 출력(with 영화 제목)

**udemy6.pig**
```
# 영화 정보
metadata = LOAD '/home/sample_data/ml-100k/u.item' USING PigStorage('|')
        AS (movieID : int, movieTitle : chararray, releaseDate : chararray, videoRelease : chararray, imdbLink : chararray);

nameLookup = FOREACH metadata GENERATE
                movieID,
                movieTitle,
                ToUnixTime(ToDate(releaseDate, 'dd-MMM-yyyy')) as releaseTime;

# 평균 4.0 초과하는 영화 id 목록 
ratings = LOAD '/home/sample_data/ml-100k/u.data' AS (userID : int, movieID : int, rating : int, ratingTime : int);

ratingsByMovieID = GROUP ratings BY movieID;

avgRatings = FOREACH ratingsByMovieID GENERATE
                group AS movieID,
                AVG(ratings.rating) as avgRating;

fiveStarMovies = FILTER avgRatings BY avgRating > 4.0;

# JOIN 해서 원하는 형태로 가공
fiveStarWithMovieData = JOIN fiveStarMovies BY movieID, nameLookup BY movieID;

DUMP fiveStarWithMovieData;
DESCRIBE fiveStarWithMovieData;
```

**실행결과**
```
영화id,평균,영화id, 영화명, 출시일
(1398,4.5,1398,Anna (1996),847843200)
(1449,4.625,1449,Pather Panchali (1955),827452800)
(1467,5.0,1467,Saint of Fort Washington, The (1993),725846400)
(1500,5.0,1500,Santa with Muscles (1996),847411200)
(1524,4.25,1524,Kaspar Hauser (1993),834105600)
(1536,5.0,1536,Aiqing wansui (1994),837993600)
(1594,4.5,1594,Everest (1998),889488000)
(1599,5.0,1599,Someone Else's America (1995),831686400)
(1639,4.333333333333333,1639,Bitter Sugar (Azucar Amargo) (1996),848620800)
(1642,4.5,1642,Some Mother's Son (1996),851644800)
(1653,5.0,1653,Entertaining Angels: The Dorothy Day Story (1996),843782400)

fiveStarWithMovieData: {fiveStarMovies::movieID: int,fiveStarMovies::avgRating: double,nameLookup::movieID: int,nameLookup::movieTitle: chararray,nameLookup::releaseTime: long}
```


---


## 테스트 #7. releaseTime으로 정렬(ORDER BY)

> ASC | DESC 명시 하지 않을 경우 ASC가 기본인듯 .. 국룰 ?! 

**udemy7.pig**
```
# 영화 정보
metadata = LOAD '/home/sample_data/ml-100k/u.item' USING PigStorage('|')
        AS (movieID : int, movieTitle : chararray, releaseDate : chararray, videoRelease : chararray, imdbLink : chararray);

nameLookup = FOREACH metadata GENERATE
                movieID,
                movieTitle,
                ToUnixTime(ToDate(releaseDate, 'dd-MMM-yyyy')) as releaseTime;

# 평균 4.0 초과하는 영화 id 목록 
ratings = LOAD '/home/sample_data/ml-100k/u.data' AS (userID : int, movieID : int, rating : int, ratingTime : int);

ratingsByMovieID = GROUP ratings BY movieID;

avgRatings = FOREACH ratingsByMovieID GENERATE
                group AS movieID,
                AVG(ratings.rating) as avgRating;

fiveStarMovies = FILTER avgRatings BY avgRating > 4.0;

# JOIN 해서 원하는 형태로 가공
fiveStarWithMovieData = JOIN fiveStarMovies BY movieID, nameLookup BY movieID;

# nameLookup::releaseTime으로 정렬(default ASC)

oldestFiveStarMovies = ORDER fiveStarWithMovieData BY nameLookup::releaseTime;

DUMP oldestFiveStarMovies;
```


**실행결과**
```
// default 오름차순 정렬인듯(ASC)
(493,4.15,493,Thin Man, The (1934),-1136073600)                                                                                                                                                                   
(604,4.012345679012346,604,It Happened One Night (1934),-1136073600)                                                                                                                                              
(615,4.0508474576271185,615,39 Steps, The (1935),-1104537600)                                                                                                                                                     
(1203,4.0476190476190474,1203,Top Hat (1935),-1104537600)                                                                                                                                                         
(613,4.037037037037037,613,My Man Godfrey (1936),-1073001600)                                                                                                                                                     
(633,4.057971014492754,633,Christmas Carol, A (1938),-1009843200)                                                                                                                                                 
(136,4.123809523809523,136,Mr. Smith Goes to Washington (1939),-978307200)
(생략..)
```


