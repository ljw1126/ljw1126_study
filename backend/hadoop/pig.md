## 참고
[https://pig.apache.org/docs/latest/basic.html#SPLIT](https://pig.apache.org/docs/latest/basic.html#SPLIT)
[https://pig.apache.org/docs/latest/func.html](https://pig.apache.org/docs/latest/func.html)



> run 이라는 bash script로 *.pig 스크립트 실행하는 것으로 판단

```bash
> cd /home/hadoop/scripts/pig/web/sandbox/temp

> file run 
run: a /usr/bin/env bash script, ASCII text executable

```

## run (bash 스크립트)
- 해당 bash script 실행하기전에 3개이상의 환경설정 상위 스크립트가 존재하는 것으로 파악.(내용은 모르겠음)
```
   #!/usr/bin/env bash
  
   . /home/hadoop/scripts/pig/common/base_tez.sh
   SCRIPT=$(dirname $(readlink -f "$0"))/temp.pig
   JAVA_OPTS="${GLOBAL_JAVA_OPTS}"

  START_DATE=20220510
  END_DATE=20220510
  
  while [ $START_DATE -le $END_DATE ]
  do
      DATE=$START_DATE
      INPUT_YEAR=`date -d "$DATE" +%Y`
      INPUT_MONTH=`date -d "$DATE" +%m`
      INPUT_DAY=`date -d "$DATE" +%d`
      OUTPUT_DIR=`date -d "$DATE" +%Y%m%d`
      DB_DATE=`date -d "$DATE" "+%Y-%m-%d"`
      DATE_TYPE=daily
      INPUT=$S3_ADDRESS/logs/web/$INPUT_YEAR/$INPUT_MONTH/$INPUT_DAY
      OUTPUT=/result/harock/test
 
      -- 동일한 폴더 있는 경우 생성 안되니 삭제 처리
      hadoop fs -rm -r $OUTPUT         
 
      -- pig script 실행
      JAVA_OPTS2="${JAVA_OPTS} -Dmapred.job.name=PigLatin:${SCRIPT}(${OUTPUT})"
      java ${JAVA_OPTS2} -cp ${CLASSPATH} org.apache.pig.Main \
          -p input=${INPUT} \
          -p output=${OUTPUT} ${SCRIPT}

      -- 결과 파일을 가져옴  
      hadoop fs -copyToLocal $OUTPUT result
 
      -- STASRT_DATE 변수값 갱신
      START_DATE=`date -d "$START_DATE +1 day" +%Y%m%d`
  done
```

## temp.pig 스크립트 

```bash
   DEFINE LogExtractor com.treenod.gaia.pig.LogExtractor();
  
   -------------------------------------------------------------------------
   -- client input
   logs = LOAD '$input' AS (ip:chararray, hyphen:chararray, user:chararray, time:chararray, method:chararray, log:chararray, status:int, bodyBytesSent:int, referer:chararray, userAgent:chararray);
  
   logs = FOREACH logs GENERATE LogExtractor(log) AS log;
  
   logs = FILTER logs BY log#'action' == 'changeModuleOption';
 
  logs = FOREACH logs GENERATE STRSPLIT(log#'url', '/').$2 AS domain,
      log#'action' AS action,
      log#'uid' AS uid,
      log#'properties'#'moduleId' AS id,
      log#'properties'#'option' AS option,
      log#'properties'#'value' AS value;
 
  actions = FOREACH (GROUP logs BY (domain, action)) GENERATE
      FLATTEN(group) AS (url, action), COUNT($1) AS cnt;    -- 
 
  -- run (bash script)에 따라 output=/result/harock/test
  -- 확인 : hadoop fs -ls -R /result/harock/test 
  -- 결과 예시 : insight.treenod.com     changeModuleOption      6
  STORE actions INTO '$output/actions' USING PigStorage();  -- /result/harock/test/actions 에 결과 저장
    

  -- uv : unique user 
  -- 결과 예시 : insight.treenod.com     1  
  uv = FOREACH (GROUP logs BY domain) {
      temp = DISTINCT logs.uid;
      GENERATE group AS domain, COUNT(temp) AS uv;
  };
 
  STORE uv INTO '$output/uv' USING PigStorage();    -- /result/harock/test/uv 에 결과 저장됨

```


## bash 스크립트와 shell 스크립트(*.sh) 차이


## 메모
```
  // run bash script에서 해당 환경변수가 어떻게 되는지 출력해봄
  SCRIPT=$(dirname $(readlink -f "$0"))/test.pig
  JAVA_OPTS="${GLOBAL_JAVA_OPTS}"

  SCRIPT >> /home/hadoop/scripts/pig/web/sandbox/temp/anderson/test.pig

  JAVA_OPTS >>
  -Dpig.additional.jars=/home/hadoop/lib/*.jar -Dpig.tmpfilecompression=true -Dpig.tmpfilecompression.codec=gz -Djava.net.preferIPv4Stack=true -Dhadoop.policy.file=hadoop-policy.xml -Djava.library.path=:/usr/lib/hadoop-lzo/lib/native:/usr/lib/hadoop/lib/native -Dexectype=tez

  ---   
   #2. LogExtracotr 이후에 생성된  

   logs = LOAD '$input' AS (ip:chararray, hyphen:chararray, user:chararray, time:chararray, method:chararray, log:chararray, status:int, bodyBytesSent:int, referer:chararray, userAgent:chararray);
  
   logs = FOREACH logs GENERATE LogExtractor(log) AS log;

   DUMP logs;

   ##2. 결과
   ([uid#ea942018-3caa-474a-92cf-7b459286c778,environment#set1,action#pageView,time#1652148464775,url#https://qa.analytic.treenod.com/pokopang/updateProgress,tid#969fac8d-6b07-4dc5-9b4e-352075e1a34c,properties#{}])
    
   // STRSPLIT 
   logs = FOREACH logs {
      url_arr = STRSPLIT(log#'url', '/', 4); // 1~N-1, [https, ,qa.analytic.treenod.com, pokopang/updateProgress ] 
 
      GENERATE
          CONCAT('/', url_arr.$3) AS url,      // 0 ~ lenght-1
          log#'action' AS action,
          log#'uid' AS uid,
          log#'properties'#'name' AS name,
          log#'properties'#'value' AS value;
   } 

   ---
   #3. url에 https://qa.analytic.treenod.com 들어가는 로그 중에 액션 집계''
  
    logs = LOAD '$input' AS (ip:chararray, hyphen:chararray, user:chararray, time:chararray, method:chararray,  log:chararray, status:int, bodyBytesSent:int, referer:chararray, userAgent:chararray);
 
    logs = FOREACH logs GENERATE LogExtractor(log) AS log;
    
    -- QA Dashboard
    logs = FILTER logs BY (log#'url' matches '.*https://qa.analytic.treenod.com.*');
    
    logs = FOREACH logs GENERATE
        STRSPLIT(log#'url', '/').$2 AS domain,
        log#'action' AS action,
        log#'uid' AS uid,
        log#'properties'#'moduleId' AS id,
        log#'properties'#'option' AS option,
        log#'properties'#'value' AS value;
    
    -- user action
    actions = FOREACH (GROUP logs BY action) GENERATE
        FLATTEN(group) AS action, COUNT($1) AS cnt;
    
    -- 결과 출력
    DUMP actions; 
   
   ##3. 결과 부분

   [main] INFO  org.apache.pig.backend.hadoop.executionengine.util.MapRedUtil  - Total input paths to process : 1
    (search,6)
    (outlink,2)
    (pageView,98)
    (tabChange,7)
    (showToolTip,15) 

    // 조건에 내 uid 제외할 경우 AND (log#'uid' != 'f36f4ef0-7311-4609-961b-a03cebda1532')
    (search,1)
    (outlink,1)
    (pageView,75)
    (tabChange,4)
    (showToolTip,7)

    ##4. 20220601  ~ 20220630 일까지 나를 제외한 
```

## 
1. FILTER BY 에 like 는 없을까?

## 참고 
1. DISTINCT 
https://pig.apache.org/docs/latest/basic.html#distinct

2. Type Construction Operators -- (), [], {}
https://pig.apache.org/docs/latest/basic.html#type-construction
    () - tuple constructor , 객체 같은 느낌
    {} - bag constructor , 배열 같은 느낌
    [] - map constructor , [key#value] , #으로 구분하는 듯, 이건 map임 ..

    ※ 좀 더 이쁘게 Operators ㅓㅇ리되어 있는 사이트
    https://data-flair.training/blogs/pig-latin-operators-statements/


```
* group by 후 DISTINCT COUNT 
https://stackoverflow.com/questions/9247296/select-count-distinct-using-pig-latin

- COUNT(DISTINCT logs.user_id) 로 하면 unique한 user_id 로 집계가 될 줄 알았는데.. 안됨.. 밖에서 변수 하나 빼고 해야 하는가 싶음 

    FOREACH logs {
        unique_user_id = DISTINCT logs.user_id 
        GENERATE 
                group AS placement,
                COUNT($1) as impressions,
                COUNT(unique_user_id) as engaged_user 
    }
```

3. matches (== expression을 사용하는 like)

```
countedLogs = FOREACH logsGroupByFields {
  unique_user_id = DISTINCT logs.user_id;

  GENERATE
    group.placement AS placement,
    (UPPER(group.ad_unit_name) matches '.*AND.*' ? 'android' : 'ios') as os, // ad_unit_name 값을 대문자로 변환 후 AND 텍스트 포함여부확인
    group.country as country,
    COUNT($1) AS impressions,
    COUNT(unique_user_id) AS engaged_user;
};
```





## 에러 
[main] ERROR org.apache.pig.tools.grunt.Grunt  - ERROR 1000: Error during parsing. Encountered " <PATH> "#DUMP "" at line 18, column 1.
Was expecting one of:
>> 주석이 있으면 안되는 듯? 주석 지우니 잘 동작함 


## hadoop 데이터 확인 관련 명령어 
```
hadoop fs -text s3://treenod-data-analysis/uid_collect/cblossom/daily/2022/06
hadoop fs -text "s3://treenod-data-analysis/applovin/2022/07/01/*" | grep CollectionReset
hadoop fs -text "s3://treenod-data-analysis/applovin/pokopang/2022/07/01/*" | grep CollectionReset
cat applovin.pig | head
hadoop fs -text "s3://treenod-data-analysis/applovin/pokopang/2022/07/01/*" | grep CollectionReset > a
cat a | awk '{print $11}'
cat a | awk '{print $11}' | sort | uniq
cat a | awk '{print $11}' | sort | uniq | wc -l
cat a | grep CollectionReset | wc -l
cat a | awk '{print $11}' | sort | uniq | wc -l
cat a | grep PremiumSummons_NPU_Level3 | wc -l
cat a | grep PremiumSummons_NPU_Level3
cat a
hadoop fs -text "s3://treenod-data-analysis/applovin/pokopang/2022/07/01/*" | grep PremiumSummons_NPU_Level3 > a
cat a | wc -l
cat a | awk '{print $11}' | sort | uniq | wc -l
vim cat a
vim a
vim applovin.pig
cat a | awk '{print $11}'
vim a
cat a | awk '{print $12}'
cat a | awk -F '\t' '{print $11}'
cat a | awk -F '\t' '{print $11}' | sort | uniq
cat a | awk -F '\t' '{print $11}' | sort | uniq | wc -l         // wc : word count , -l : line 단위
pwd
ls
rm a
cat a | awk -F '\t' '{print $11}' | sort | uniq | wc
cat a | wc -l
vim aw
cat aw | wc
vim applovin.pig
hadoop fs -text "s3://treenod-data-analysis/applovin/pokopang/2022/07/01/*" | awk -F '\t' '{print $7}' | sort | uniq
```