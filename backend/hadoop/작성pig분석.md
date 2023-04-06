
```bash
# logs, event(이벤틈여), params[] 의 경우 text log를 변환해서 읽음
bingo = FILTER logs BY event == 'PLAYEND' AND params#'L_GMOD' = 'BINGO';

bingo = FOREACH bingo GENERATE 
    encmid,        -- uid 같은거 
    country,
    params#'L_STG' AS stage,
    params#'L_PWIN' AS win;

---
# 플레이 유저수 (중복x)
total_play_user = GROUP bingo BY (country, stage);

    DESCRUBE total_play_user;
    ✨ {
        (group: (country: chararray, stage: bytearray), bingo: {(encmid: bytearray, country: chararray, stage: bytearray, win: bytearray)}), 
        .. }
    👩‍💻 

// group 
total_play_user = FOREACH (GROUP bingo BY (country, stage)) {
    dist_uid = DISTINCT bingo.encmid;  // group by 대상 bingo{} 의 모든 ()에 대해 DISTINCT 수행 
    
    GENERATE FLATTEN(group) AS (country, stage), 
        'stage_play|bingo' AS data_key,
        'play_user' AS data_type,
        COUNT(dist_uid) AS value;
}

    DUMP total_play_user;
    ✨ {(country, stage, data_key, data_type, value), ... }
    ...
    (all,1,stage_play|bingo,play_user,15954)
    (all,2,stage_play|bingo,play_user,15570)
    (all,3,stage_play|bingo,play_user,15986)
    (all,4,stage_play|bingo,play_user,16070)
    (all,5,stage_play|bingo,play_user,15851)
    (all,6,stage_play|bingo,play_user,16246)
    (all,7,stage_play|bingo,play_user,15943)
    (all,8,stage_play|bingo,play_user,16423)
    (all,9,stage_play|bingo,play_user,16062)
    (all,10,stage_play|bingo,play_user,16522)
    (all,11,stage_play|bingo,play_user,16465)
    (all,12,stage_play|bingo,play_user,16607)
    (all,13,stage_play|bingo,play_user,16358)
    (all,14,stage_play|bingo,play_user,16562)
    (all,15,stage_play|bingo,play_user,16699)
    (all,16,stage_play|bingo,play_user,16870)
    (all,17,stage_play|bingo,play_user,17108)
    (all,18,stage_play|bingo,play_user,17078)

---
# 클리어 유저 수 (중복x)
total_clear_user = FILTER bingo BY win == 'WIN';
total_clear_user = GROUP total_clear_user BY (country, stage);
    
    DESCRIBE total_clear_user;
    {(group: (country: chararray,stage: bytearray), total_clear_user: {(encmid: bytearray,country: chararray,stage: bytearray,win: bytearray)}), ...}

total_clear_user = FOREACH (GROUP total_clear_user BY (country, stage)) {
    dist_uid = DISTINCT total_clear_user.encmid;

    GENERATE FLATTEN(group) AS (country, stage),
        'stage_play|bingo' AS data_key, 
        'clear_user' AS data_type, 
        COUNT(dist_uid) AS value;
}

    DUMP total_clear_user;
    ✨ {(country, stage, data_key, data_type, value), ... } 
    ...
    (all,1,stage_play|bingo,clear_user,13740)
    (all,2,stage_play|bingo,clear_user,15423)
    (all,3,stage_play|bingo,clear_user,14208)
    (all,4,stage_play|bingo,clear_user,13979)
    (all,5,stage_play|bingo,clear_user,15149)
    (all,6,stage_play|bingo,clear_user,9744)
    (all,7,stage_play|bingo,clear_user,12073)
    (all,8,stage_play|bingo,clear_user,9442)
    (all,9,stage_play|bingo,clear_user,9568)
    (all,10,stage_play|bingo,clear_user,9550)
    (all,11,stage_play|bingo,clear_user,10549)
    (all,12,stage_play|bingo,clear_user,8022)
    (all,13,stage_play|bingo,clear_user,7865)
    (all,14,stage_play|bingo,clear_user,7886)
    (all,15,stage_play|bingo,clear_user,6712)
    (all,16,stage_play|bingo,clear_user,6039)
    (all,17,stage_play|bingo,clear_user,3219)
    (all,18,stage_play|bingo,clear_user,3097)

---
# 참여 유저 수 (누적, 플레이 유저수랑 접근 개념은 동일)
## country, stage, data_key, data_type, value 형으로 구하려고 함 
## 튜플1. country 별 참여 유저 수
## 튜플2. country 별 스테이지 번호 
## 튜플1, 2 join 해서 원하는 결과 형태 구함

// 이름 별로별로
# 국가별 참여 유저 수
total_user_by_country = FOREACH (GROUP bingo BY country) {
    dist_uid = DISTINCT bingo.encmid;
    GENERATE group AS country, COUNT(dist_uid) AS value;
}

# 국가별 스테이지 번호
country_and_stage = FOREACH (GROUP bingo BY (country, stage)) {
    GENERATE FLATTEN(group) AS (country, stage);
}

// $0 파라미터는 country에 해당함
total_user = JOIN total_user_by_country BY $0 LEFT OUTER, country_and stage BY $0;

    DESCRIBE total_user;
    👩‍💻 join 이기 때문에 파라미터 호출시 'RelationName::filedName' 형태로 호출해야 함!
    {(total_user_by_country::country: chararray,total_user_by_country::value: long,country_and_stage::country: chararray,country_and_stage::stage: bytearray), ..}

participate_user_count = FOREACH total_user GENERATE
    country_and_stage::country AS country, 
    country_and_stage::stage AS stage,
    'stage_play|bingo' AS data_key,
    'participate_user_count' AS data_type,
    total_user_by_country::value AS value;


---
// 이걸 맞춰서 넣어주면 끝 입니다. (*중요)필드 순서는 최상단 insert 문에 맞춰서 작성
result = UNION totla_play_user, totla_clear_user, participate_user_count;
result = FOREACH result GENERATE 
    country AS country,
    'pkv-line' AS service,
    '$date_type' AS date_type,      -- $date_type은 pig 구동시 전달 받은 파라미터
    '$db_date' AS stat_date,
    data_type AS data_type,
    data_key AS data_key,
    stage AS meta:chararray,
    value AS value:double
    value AS update_value:double;

STORE result INTO 'unused' USING DBStorage();
#STORE result INTO $output USING PigStorage('\t');     // delimiter를 \t로 구분해서 hdfs $output 경로에 결과 저장
```

