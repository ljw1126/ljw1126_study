- Postgresql 9.6 설치
  [https://www.enterprisedb.com/downloads/postgres-postgresql-downloads](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- docker로 postgresql 설치 방법 찾음 

- **Postgresql** 이란 ? 
  [https://mangkyu.tistory.com/71](https://mangkyu.tistory.com/71)
- (211009) 바탕화면에 있는 docker 실행해서 Postgresql 실행 후 dbeaver로 연결해서 사용하면 됨 
  - 포트 5432
  - 관리자 계정 tester/1234!@#$
  - db명 test
   
```
> docker pull postgres:9.6.11
> docker images 
> docker run -d -p 5432:5432 --name pgsql -e POSTGRES_PASSWORD=postgresql postgres

[옵션 설명] 
--name pgsql : 컨테이너 이름을 pgsql 지정
-d : 컨테이너를 백그라운드로 실행
-p 5432:5432 : 호스트와 컨테이너 간의 포트연결 , 호스트에서 5432 포트 접속시 컨테이너 5432 포트로 포워딩 됨
-e : 환경변수 설정
POSTGRES_PASSWORD=postgresql : 컨테이너 내 환경변수 설정, root 계정의 패스워드를 postgresql로 지정.
postgres : 다운받은 이미지명


> docker ps -a 
> docker exec -it pgsql bash      // 컨테이너 접속

$ cat /etc/*release               // OS 버전 확인 
PRETTY_NAME="Debian GNU/Linux 11 (bullseye)"
NAME="Debian GNU/Linux"
VERSION_ID="11"
VERSION="11 (bullseye)"
VERSION_CODENAME=bullseye
ID=debian
HOME_URL="https://www.debian.org/"
SUPPORT_URL="https://www.debian.org/support"
BUG_REPORT_URL="https://bugs.debian.org/"


$ psql -U postgres //postgres db 접속 
postgres=# CREATE USER tester PASSWORD '1234!@#$' SUPERUSER;
CREATE ROLE

postgres=# CREATE DATABASE test OWNER tester; 
CREATE DATABASE

postgres=# \l               // db 목록 확인
postgres=# \c test tester   // \c [db_name] 명령어를 통해 다른 데이터베이스에 접속할 수 있다.
postgres=# \u               // 계정확인 
postgres=# \q               // \q 명령어를 통해 psql을 종료할 수 있다.

// import는 귀찮아서 dbeaver로 했는데 ..
1. dbeaver로 *.sql 파일 실행
2. \i 파일명.sql 로 실행        ///i : 외부 파일을 통한 Query 실행

// 일단 volume 설정도 뺌 

※ 도움말 
\?
※ SQL에 대한 명령어     
\h
※ 백업(volume) 설정 
https://judo0179.tistory.com/96
※ postgresql 기본 명령어 
https://kwomy.tistory.com/8
https://browndwarf.tistory.com/51


※ import 귀찮아서 dbeaver로 실행함  // 특정 스키마명(데이터베이스명) 기재되어 있지 않으므로  
alt + x 
//아래 명령어는 dump랑 import인데 제대로 사용 못함 
https://www.prisma.io/dataguide/postgresql/inserting-and-modifying-data/importing-and-exporting-data-in-postgresql

```

# ch2-1
## 기본 검색 및 정렬 
- 기초라서 생략
```
SELECT [컬럼명] (','를 통해서 구분)
FROM [테이블명]
WHERE [조건들] (and, or연산자를 통해서 구분)
ORDER BY [컬럼명들] (','를 통해서 구분)
```



## 그룹 제어문
```
SELECT [GROUP BY에서 사용된 컬러명들, 집계함수]
FROM [테이블명]
WHERE [조건들]
GROUP BY [컬럼명등] (','를 통해서 구분)
HAVING [GROUP BY절에 해당하는 조건들] (','를 통해서 구분) 

# 예시 1번 - caddr 대신 cname으로 표기되어 있어서 쿼리 동작 안함 (수정)
SELECT caddr, count(caddr) 
FROM tcustomer 
group by caddr
having COUNT(CADDR) > 2;
```

## 분기문 
```
1. SIMPLE_CASE_EXPRESSION

SELECT 
      (
        CASE [컬럼명] WHEN [비교값1] THEN [반환값1]
                      WHEN [비교값2] THEN [반환값2]
                      WHEN [비교값3] THEN [반환값3]
                      WHEN [비교값4] THEN [반환값4]
                      WHEN [비교값5] THEN [반환값5]

        ELSE [WHEN절 이외의 조건일때 반환될 값]
        END
        ...   
      ) AS [별칭 컬럼명] 
FROM [테이블명]

2. SEARCHED_CASE_EXPRESSION

SELECT 
      (
        CASE WHEN [조건문1] THEN [반환값1]
             WHEN [조건문2] THEN [반환값2]
             WHEN [조건문3] THEN [반환값3]
             WHEN [조건문4] THEN [반환값4]
             WHEN [조건문5] THEN [반환값5]

        ELSE [WHEN절 이외의 조건일때 반환될 값]
        END
        ...   
      ) AS [별칭 컬럼명] 
FROM [테이블명]

# 예시 dnumber에 따라 부서명 출력되도록 조회 (조회결과 동일)
select 
		ename, 
		dnumber,
		(
			case dnumber when 'D1001' then '문구생산부'
						 when 'D2001' then '가구생산부'
						 when 'D3001' then '악세사리생산부'
						 when 'D4001' then '전자기기생산부'
						 when 'D5001' then '음료생산부'
						 else '부서 없음'
			end 
		) as "부서명"
from temployee t 
order by ename;


select 
		ename, 
		dnumber,
		(
	        case when dnumber = 'D1001' then '문구생산부'
				 when dnumber = 'D2001' then '가구생산부'
				 when dnumber = 'D3001' then '악세사리생산부'
				 when dnumber = 'D4001' then '전자기기생산부'
				 when dnumber = 'D5001' then '음료생산부'
				 else '부서 없음'
			end 
		) as "부서명"
from temployee t 
order by ename;

```

## 집합연산 UNION, UNION ALL 
- 두 쿼리 결과를 합침
  - 컬럼 수가 동일해야 함 
  - 컬럼 데이터 타입 동일해야 함 
- UNION : **중복결과 삭제 표출 **
- UNION ALL : **중복결과 포함 표출**

```
# 예제 테이블 존재하지 않음 
```

# ch2-2 
## 순위집계 RANK(), DENSE_RANK(), ROW_NUMBER() 
```
1. RANK 
SELECT
        RANK() OVER(PARTITION BY [그룹할 컬럼들] ORDER BY [순위를 매길때 사용할 컬럼들] desc 또는 asc 추가로 NULLS LAST )
FROM [테이블명];
// 동일한 3등이 3명이면 3명다 3위 처리하고 다음 순위는 6등으로 처리
※ ORDER BY 컬럼 NULLS LAST        - 컬럼 정렬 후 null을 마지막에 표시
※ ORDER BY 컬럼 NULLS FIRST       - 처음부터 null 표시한 다음 컬럼 정렬


2. DENSE_RANK
SELECT
        DENSE_RANK() OVER(PARTITION BY [그룹할 컬럼들] ORDER BY [순위를 매길때 사용할 컬럼들])
FROM [테이블명];
// 동일한 3등이 3명이면 3명다 3위 처리하고 다음 순위는 4등으로 처리

3. ROW_NUMBER
SELECT
        ROW_NUMBER() OVER(PARTITION BY [그룹할 컬럼들] ORDER BY [순위를 매길때 사용할 컬럼들])
FROM [테이블명];
// 동일한 값에 대해서도 각각의 고유 순위 처리 (점수는 동일해도 3,4,5위 처리)

# 예시 테이블 tscore 없음 

```

## 조인 INNER/OUTER/FULL OUTER/SELF/CROSS JOIN 
```
1. INNER JOIN  = 교집합 , INNER 생략 가능 
SELECT * 
FROM [테이블1] as A INNER JOIN [테이블2] as B ON A.key = B.key

# 예시. 부서번호(dnumber) 존재하는 직원만 쿼리 조회됨
select 
       tde.dname, 
	   tem.ename 
from tdepartment as tde join temployee as tem on tde.dnumber = tem.dnumber 
order by tem.ename;

2. OUTER JOIN  = LEFT OUTER JOIN , RIGHT OUTER JOIN 이 있음 
- left 의 경우 왼쪽 테이블 기준으로 표출하는데 오른쪽 테이블에 key값이 없으면 null로 표시
- right 의 경우 오른쪽 테이블 기준으로 표출하는데 왼쪽 테이블에 key값이 없으면 null로 표시
- OUTER 생략하고 사용가능 
# 예시 테이블 존재 x 

3. FULL OUTER JOIN
- 좌측, 우측 테이블 상관없이 데이터가 없으면 null로 표출됨
# 예시 테이블 존재 x 

4. SELF JOIN     ----------- 약간 이해 안됨 
- 하나의 테이블로 JOIN 하는것 
SELECT * 
FROM [테이블1] as A JOIN [테이블1] as B ON A.name = B.manager;
# 예시 테이블 존재 x 

5. CROSS JOIN 
- 좌측과 우측 결합하여 모든 경우의 수 표출 
- 레코드수 많을수록 DB 부하 증가 시킬 수 있으므로 주의요함 
SELECT * 
FROM [테이블1] as A CROSS JOIN [테이블2] as B 
# 예시 테이블 존재 x 
```

# 집계/문자열/날짜/그외 함수

## 집계 함수
```
1. MAX([컬럼명])
   명시된 컬럼내 값들 중 최대값을 반환한다.

2. MIN([컬럼명])
   명시된 컬럼내 값들 중 최소값을 반환한다. 

3. COUNT([컬럼명])
   명시된 컬럼내 값의 전체 행 수를 반환한다 (이때 null 값은 제외)

4. SUM([컬럼명])
   명시된 컬럼의 데이터 타입이 숫자일 경우, 해당 컬럼내 모든 데이터의 합을 반환함 (null값 제외)

5. AVG([컬럼명])
   명시된 컬럼의 데이터 타입이 숫자일 경우, 해당 컬럼내 모든 데이터의 평균을 반환함(null값 제외)

# 예시 테이블 존재 x 
```

## 문자열 함수 
```
1. SUBSTRING(string, int, int)
    첫번째 명시한 문자열의 부분 문자열 자르기 

2. LTRIM(string) , LTRIM(string, string) , RTRIM(string) , RTRIM(string, string)
    명시한 문자열의 좌측/우측 공백을 제거 또는 특정 문자 제거 

3. LPAD(string , n , string) / RPAD (string , n , string) 
    첫 번째 명시한 문자열에 길이가 n이 되도록 좌측/우측부터 세번째 명시한 문자열로 채운 표현식을 반환함 
    // n 길이가 될때까지 left 또는 right부터 세번째 string을 넣기 

4. REPLACE(string, string_pattern, string_replacement)
    첫 번째 명시된 문자열 중 string_pattern 에 해당하는 문자열을 string_replacement 문자열로 반환 

5. LENGTH(string)
    명시된 문자열의 길이를 구하여 반환 
```

## 날짜 함수 
```
1. NOW() 
    현재 날짜 및 시간을 출력 

2. AGE(timestamp, timestamp) / AGE(timestamp)
    두 날짜 사이의 시간차이를 계산/ 현재 날짜와 첫 번째 명시한 날짜의 시간 차이를 계산 
    ex1.) SELECT AGE(timestamp '2020-01-20', timestamp '2015-01-15'); // 5years 5days
    ex2.) SELECT AGE(timestamp '2015-01-15'); // 6 years 3mons ..(생략) , 현재날짜와 첫번째 매개변수 날짜와 차이 리턴 

3. DATE_PART(text, timestamp)
    두 번째 명시한 timestamp 에서 첫 번재 명시한 날짜 키워드 인자에 해당하는 값을 추출함 
    ex) SELECT DATE_PART('day', timestamp '2020-06-15'); // day 추출해서 15 리턴 

4. DATE_TRUNC(text, timestamp)
    두 번째 명시한 timestamp에서 첫 번째 명시한 날짜 키워드 인자에 해당하는 값 이하의 날짜 데이터를 default 처리하고 반환 
    ex) SELECT DATE_TRUNC('month', timestamp '2020-05-16 00:53:12') // 결과 2020-05-01 00:00:00 ,month 미만은 다 초기화 하라는 뜻

```

## 그외 함수 
```
1. TO_CHAR(timestamp, text포맷)
   첫번째로 명시된 timestamp 값을 두번째 인자의 포맷 문자열('YYYYMMDD')로 변환하여 반환 

2. COALESCE(value, ex1, ex2, ..)
    첫번째로 명시된 인자가 null 일 경우 두 번째 인자 반환, 두번째 인자가 null 이면 세번재 인자 반환..

3. CAST(source_type as target_type)
    첫번재로 명시된 source_type를 두번째 인자로 명시된 target_type으로 변환하여 반환 

4. ROUND(v numeric, s int)
    첫번째로 명시된 v 값을 소수점 s 자리까지 반올림하고 s 자리 미만은 버림 
```
[https://www.postgresql.org/docs/9.6/functions-formatting.html](https://www.postgresql.org/docs/9.6/functions-formatting.html)