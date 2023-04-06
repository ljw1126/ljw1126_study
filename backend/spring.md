
# url 로 파일 다운로드시 읽는 방법 (with CSVReader)
https://stackoverflow.com/questions/15513697/read-remote-csv-file-using-opencsv
https://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily/21657510#21657510

## openCsv 포스팅 
https://gksdudrb922.tistory.com/191

## openCsv 공식 
http://opencsv.sourceforge.net/


## tar.gz 으로 압축 
https://javamana.com/2021/07/20210727022326883H.html

## jvm heap memory dump  (이것저것 java 코드 가이드 있는 사이트)
https://www.geeksforgeeks.org/how-to-generate-jvm-heap-memory-dump/

## PATTERN 관련 
https://gngsn.tistory.com/53

## Spring @Value Default Value 
https://www.concretepage.com/spring-5/spring-value-default#String

## tar.gz 샘플 
```
  // pom.xml 
  <dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-compress</artifactId>
    <version>1.20</version>
  </dependency> 

  // tar.gz 샘플 로직  
  public static final String TAR_GZIP_PATH = "d:\\ad_revenue_report.tar.gz";
    @Test
    public void compressMultiFileToTarGzipTest() {
        Path filePath1 = Paths.get(FILE_NAME); // 압축대상 파일
        Path filePath2 = Paths.get("d:\\ad_revenue_report_back.csv");

        List<Path> paths = Arrays.asList(filePath1, filePath2);

        Path output = Paths.get(TAR_GZIP_PATH); // 압축 결과 파일

        try (OutputStream outputStream = Files.newOutputStream(output);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             GzipCompressorOutputStream gzOutputStream = new GzipCompressorOutputStream(bufferedOutputStream);
             TarArchiveOutputStream tarArchiveOutputStream = new TarArchiveOutputStream(gzOutputStream);
             ) {

            for (Path path : paths) {
                if (!Files.isRegularFile(path)) {
                    throw new IOException("support only file");
                }

                File file = path.toFile();

                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(file, path.getFileName().toString());
                tarArchiveOutputStream.putArchiveEntry(tarArchiveEntry);
                Files.copy(path, tarArchiveOutputStream);
                tarArchiveOutputStream.closeArchiveEntry();

                file.delete(); // 압축 후 삭제
            }

            tarArchiveOutputStream.finish();

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(new File(TAR_GZIP_PATH).exists());
    }
```

## RestTemplate 클래스 관련 
https://e2e2e2.tistory.com/15#message-converter
https://hoonmaro.tistory.com/46#httpmessageconverter-%EA%B5%AC%ED%98%84%EC%B2%B4-%EB%AA%A9%EB%A1%9D
https://velog.io/@kim_sunnnny/what-is-applicationoctet-stream



## spring boot property 관련
aws.amazon.com/ko/console/
https://www.baeldung.com/spring-value-defaults
https://blog.kingbbode.com/17

- 230309 aws secrets manager에 설정된 {속성 : 값}을 읽어오는 테스트 진행 
    - aws secrets manager 활성화시 1순위로 덮어 씌움 
    - 비활성화시 시스템 변수, 그리고 사용자 설정 변수 순으로 덮어 씌우는 것으로 판단됨 
        - user.name이 시스템 변수로 있다는 걸 기억 못했음. (window user name 호출해버림 😅)


## Annotation 관련 
https://velog.io/@jkijki12/annotation


## url에 없는  경로 입력시 tomcat 에러 노출 방지
https://www.leeby.one/posts/spring.web.resources.add-mappings-%EC%98%B5%EC%85%98/

v2.1 -> v2.7로 올리니 속성명이 변경됨 >> spring.web.resources.add-mappings

참고. spring boot 버전업
https://devocean.sk.com/blog/techBoardDetail.do?ID=164482

--- 

#### 참고 
build.gradle의 동작원리 한 번에 정리하기 
    https://kotlinworld.com/321
The Basics of Java Generics (밸덩)
    https://www.baeldung.com/java-generics
Spring Boot Starter Parent (밸덩)
    https://www.baeldung.com/spring-boot-starter-parent