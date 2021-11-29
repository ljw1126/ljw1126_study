## JVM과 GC(garbege collection)

#### JVM(Java Virtual Machine)
- 운영체제의 메모리 영역에 접근하여 메모리를 관리하는 프로그램
- 메모리 관리, Garbage Collector 수행 

// javac와 같은 front build/compiler도구를 통해 개발자가 작성해 놓은 코드를 byte code로 변환하면서 추상적 문법을 분석하고, JIT 를 거쳐 JVM을 통해 구현이 된다함. 

<center>
    <img src="/images/spring/jvm.png" alt=""/>
</center>


```
## 기록 

- JVM의 GC는 Mark & Sweep 방식을 사용
- root space에서 참조하는 객체가 heap 에 존재하는 mark한 뒤 unreachable 객체는 sweep 함
- Runtime data area의 root space는 다음과 같다 
  - JVM Language Stack 
  - Native Method Area 
  - Method Area 

## Class Loader 
- 생성된 class 파일을 Runtime Data Area에 적재하는 역할 

## Execution Engine 
- 메모리에 적재된 클래스를 기계어로 변경해 명령어 단위로 실행하는 역할 (인터프리터, JIT 방식)

## G.C(Garbage Collection)
- heap 메모리 영역에 생성된 객체 중 reachability를 잃은 객체를 탐색 후 제거함

## Runtime Data Area  

*Thread 공통 영역*
1. Heap (GC 대상)
 - 어플리케이션 실행시 동적 생성된 객체 인스턴스를 저장하는 영역
 - GC의 대상 
 - 모든 Thread에서 공유하는 영역이므로 동기화 문제 발생가능 
   - immutable 객체 사용권장
2. Method area (root space)
 - 프로그램의 클래스 구조를 메타 데이터처럼 가지고 있고, 메서드의 코드를 저장함 
 - class loader가 적재한 클래스(또는 인터페이스)에 대한 메타 데이터 정보가 저장됨
 - 해당 영역에 등록된 class 만이 heap에 생성가능
 - 논리적으로 Heap 영역에 포함되어 PermGem 영역에 속했으나, Java8 이후 Metaspace라는 OS관리하는 영역으로 옮김

*Thread별 생성 영역*
3. JVM Language Stack (root space)
 - 메서드 호출을 stack frame 이라는 블록으로 쌓으며, 로컬변수, 중간 연산 결과가 저장되는 영역
 - 메서드 호출 종료시 stack에서 제거함
4. Native method stack (root space)
 - java 외의 언어로 작성된 native code를 위한 stack 
 - low level(C/C++) 코드를 실행하는 영역 
5. Pc register (root spcae)
 - 쓰레드가 현재 실행할 스택 프레임의 주소를 저장하고 있음(가르킴)


```

#### Garbage Collector 란?
- 프로그램이 **동적으로 할당했던 메모리 영역(Heap)** 중 **필요 없게 된 영역**을 알아서 해제 
  - 할당받은 메모리 해제가 수동으로 되면 제대로 되지 않을 경우 memory leak(메모리 누수, 컴퓨터 프로그램이 필요하지 않은 메모리를 계속 점유하고 있는 현상) 발생
- JVM의 heap 영역에서 사용하지 않는 객체를 **삭제하는 프로세스**를 말함
- Garbage collection was invented to simplify manual memory management 
  - 동적으로 할당한 메모리(Heap) 영역 중 사용하지 않는 영역을 탐지하여 해제하는 기능 

#### GC 장단점 
- 장점
  - 메모리 누수 막음
  - 해제된 메모리에 접근 차단
  - 해제한 메모리에 대한 이중 해제 차단 

- 단점
  - GC 작업은 순수 오버헤드
  - 개발자는 언제 GC가 메모리를 해제하는지 모름 
    - 실시간성이 강조되는 프로그램의 경우 GC에게 맞기는건 맞지 않을 수 있다. 


#### Stack, Heap 
- Stack 
  - 정적으로 할당한 메모리 영역 
  - 원시 타입의 데이터가 값과 함께 할당, Heap 영역에 생성된 Object 타입의 데이터의 참조 값 할당(Reference Type)
  - Heap 영역의 Object를 가리키는 참조 변수가 Stack에 할당됨 
- Heap 
  - 동적으로 할당된 메모리 영역 
  - 모든 Object 타입의 데이터가 할당(Primitive Type도 포함, Object 타입의 데이터들 String, List등 포함)

#### GC 알고리즘 - Reference Counting 
- reference counting 을 측정해서 0이되면 지워줌 
- 객체 순환참조시(사이클) 문제 발생
 
#### GC 알고리즘 - Mark & Sweep
- Reference Counting 알고리즘의 순환참조 문제 해결한 알고리즘
- ① GC 가 Stack의 모든 변수를 스캔하면서 각각 어떤 객체를 참조하고 있는지 찾아서 마킹함
- ② Reachable Object가 참조하고 있는 객체도 찾아서 마킹함
- ③ 마킹되지 않는 객체를 Heap에서 제거(sweep)
  - (선택적) 알고리즘에 따라 compaction 과정이 추가되기도 함 
  - Sweep 후에 분산된 객체들을 Heap의 시작 주소로 모아 메모리가 할당된 부분과 그렇지 않은 부분으로 나눔
- 특징
  - 의도적으로 GC를 실행시켜야 함 
  - 어플리케이션 실행과 GC 실
  - 행이 병행됨
  - 그래서 최적화가 어려움 

#### Reachability 
- GC Roots 부터 탐색했을때 어떤 객체에 유효한 참조가 존재한다면 Reachable, 그렇지 않다면 Unreachable이라고 함 
- **Heap영역에 있는 Unreachable 객체는 GC의 수거 대상**
- GC Roots 대상 
  - stack 영역의 데이터들
  - method 영역의 static 데이터들
  - JNI(Java Native Interface)에 의해 생선된 객체들
- GC Roots가 참조하고 있는 Heap 영역의 데이터가 있으면 Reachable, 없으면 Unreachable

#### Heap의 구조 
- Young Generation : 새로운 객체들이 할당되는 영역
  - Eden 
  - Survivor0
  - Survivor1
- Old Generation : Young Generation영역에서 오랫동안 살아남은 객체들이 존재하는 영역(객체의 age-bit값이 기준치 만족할때 young에서 old로 이동(=promotion)함)
- meta space : 가바지 컬렉션 시에 필요한 클래스와 메소드의 요약 정보가 존재하는 영역 

// 이미지 넣기 
Eden 에 Minor GC 발생 > survior 0 또는 survior 1 번갈아 가며 객체 저장 > unreachable 객체 지움
// (규칙)이때 survior 0 또는 survior 1 둘중 하나는 반드시 비워져 있어야함 (둘다 꽉찼으면 문제가 있다는거)

#### Weak Generational Hypothesis 
- 1. Most allocated objects are not referenced (considered live) for long, that is, they die young 
  - 대부분 객체는 금방 접근 불가능한 상태(unreachable)가 된다. **즉, 금방 garbage가 된다**
- 2. Few references from older to younger objects exist.
  - 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재한다. 

Stop the world 시간을 줄이는게 GC의 중요한 부분이라고함 

#### stop-the-world
- GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것 
- GC를 실행하는 쓰레드 외의 모든 쓰레드가 작업을 중단한다. 
  - stop-the-world 시간을 최적화 하는게 어려움 
  
#### GC의 종류 
- ① Serial GC 
  - GC를 처리하는 쓰레드가 1개(싱글 쓰레드)
  - 다른 GC에 비해 stop-the-world 시간이 길다.
  - Mark-Compact(Sweep 포함) 알고리즘 사용
- ② Parallel GC 
  - Java 8의 default GC
  - 멀티코어 환경에서 Young 영역의 GC를 멀티 쓰레드로 수행 
  - Serial GC에 비해 stop-the-world 시간 감소  
  - age-bit 기준 15
- ③ Parallel Old GC
  - Parallel GC를 개선
  - Old 영역에서도 멀티 쓰레드 방식의 GC 수행
  - Mark-Summary-Compact 알고리즘 사용 
    - sweep : 단일 쓰레드가 old 영역 전체를 훓는다.
    - summary : 멀티 쓰레드가 old 영역을 분리해서 훓는다.
- ④ CMS GC(Concurrent Mark Sweep)
  - stop-the-world 시간을 줄이기 위해 고안됨 
  - GC작업을 어플리케이션과 동시 실행 => stop-the-world 최소화
  - 하지만 메모리와 CPU를 많이 사용하고 , compation 작업이 기본제공 x 
  - compact 과정이 없음 => 메모리 단편화 문제 
    - Initial Mark : GC Root에서 참조하는 객체들만 우선 식별
    - Concurrent Mark : 이전 단계에서 식별한 객체들이 참조하는 모든 객체 추적
    - Remark : 이전 단계에서 식별한 객체를 다시 추적. 추가되거나 참조가 끊긴 객체 확정
    - Concurrent Sweep : 최종적으로 unreachable 객체들을 삭제 
- ⑤ G1 GC(Garbage First)
  - CMS GC를 개선
  - java 9+의 default GC 방식
  - Heap을 일정한 크기의 **Region**으로 나눔
  - 전체 Heap이 아닌 Region 단위로 탐색 
  - compact 진행 
  - stop-the-world 최적화 실행 
  - 




#### 참고 기술 블로그 
[https://d2.naver.com/helloworld/1329](https://d2.naver.com/helloworld/1329 'NAVER D2 - Java Garbage Collection')
[https://d2.naver.com/helloworld/329631](https://d2.naver.com/helloworld/329631)
[https://d2.naver.com/helloworld/6043](https://d2.naver.com/helloworld/6043)
[https://d2.naver.com/helloworld/37111](https://d2.naver.com/helloworld/37111)

[https://developer.mozilla.org/ko/docs/Web/JavaScript/Memory_Management](https://developer.mozilla.org/ko/docs/Web/JavaScript/Memory_Management '자바스크립트의 메모리관리')
[https://velog.io/@litien/JVM-%EA%B5%AC%EC%A1%B0](https://velog.io/@litien/JVM-%EA%B5%AC%EC%A1%B0 '피누.log - JVM 구조')
[https://www.holaxprogramming.com/2013/07/16/java-jvm-runtime-data-area/](https://www.holaxprogramming.com/2013/07/16/java-jvm-runtime-data-area/)
[https://jithub.tistory.com/40](https://jithub.tistory.com/40)

#### 참고 영상
[https://www.youtube.com/watch?v=FMUpVA0Vvjw](https://www.youtube.com/watch?v=FMUpVA0Vvjw '[10분 테코톡] 🤔 조엘의 GC')
[https://youtu.be/Fe3TVCEJhzo](https://youtu.be/Fe3TVCEJhzo '[10분 테코톡] 🐥엘리의 GC')