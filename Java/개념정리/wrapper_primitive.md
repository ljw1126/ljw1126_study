## Wrapper Class Type(참조형) 과 primitive type(기본형)
- JDK1.5부터 오토박싱과 언박싱을 지원함 

```java
public class Wrapper_Ex {
    public static void main(String[] args)  {
        Integer num = 17; // 자동 박싱 , 객체가 됨 
        int n = num; //자동 언박싱, 기본형이 됨 
        System.out.println(n);
    }
}
```

- 문자열을 기본형 타입 변환 

```java
public class Wrapper_Ex {
    public static void main(String[] args)  {
        String str = "10";
        String str2 = "10.5";
        String str3 = "true";
        
        byte b = Byte.parseByte(str);
        int i = Integer.parseInt(str);
        short s = Short.parseShort(str);
        long l = Long.parseLong(str);
        float f = Float.parseFloat(str2);
        double d = Double.parseDouble(str2);
        boolean bool = Boolean.parseBoolean(str3);
		
        System.out.println("문자열 byte값 변환 : "+b); //10
        System.out.println("문자열 int값 변환 : "+i); //10
        System.out.println("문자열 short값 변환 : "+s); //10
        System.out.println("문자열 long값 변환 : "+l); //10
        System.out.println("문자열 float값 변환 : "+f); //10.5
        System.out.println("문자열 double값 변환 : "+d); //10.5
        System.out.println("문자열 boolean값 변환 : "+bool); //true
    }
}
```

- "=="에 대한 String과 Integer 차이 
```java
    public static void main(String[] args) {
        Integer num = new Integer(10);
        int i = 10;
        // Integer , int의 경우 == 해도 값이 동일하면 true 출력됨 
        System.out.println(num == i);         // true 
        System.out.println(num.equals(i));    // true 
        
        String aa = "hello";
        String bb = "world";
        String cc = "hello";
        String dd = new String("world");
        String ee = "hell";
        ee += "o";
        
        // String의 경우 서로 다른 객체이기때문에 bb == dd 처럼 false가 출력됨
        System.out.println(aa==bb); //false
        System.out.println(aa==cc); //true
        System.out.println(bb==dd); //false
        System.out.println(bb.equals(dd)); //true
        System.out.println(ee==aa); //false
        System.out.println(ee.equals(aa)); //true
    
    }

```

#### 참고 
[https://coding-factory.tistory.com/547](https://coding-factory.tistory.com/547)
[https://kutar37.tistory.com/entry/%EC%9E%90%EB%B0%94-String-%ED%81%B4%EB%9E%98%EC%8A%A4%EC%9D%98-%EB%A9%94%EC%86%8C%EB%93%9C](https://kutar37.tistory.com/entry/%EC%9E%90%EB%B0%94-String-%ED%81%B4%EB%9E%98%EC%8A%A4%EC%9D%98-%EB%A9%94%EC%86%8C%EB%93%9C)
[https://league-cat.tistory.com/407](https://league-cat.tistory.com/407 'primitive 타입의 범위')

 8bit  16bit 32bit 64bit
byte, short, int, long 
            float , double