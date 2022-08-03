
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