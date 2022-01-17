## 작업기록 
```
1. build.gradle 의존성 추가
 implementation 'org.hibernate:hibernate-core:5.2.5.Final'

2. 인텔리제이에서 h2 database 연결 
https://github.com/HomoEfficio/dev-tips/blob/master/IntelliJ%EC%97%90%EC%84%9C-H2-DB-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B3%A0-JPA-Console-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0.md



※ 나중에 다시 읽기 
1. SessionFactory 생성하는거랑 config.xml 따로 만든거 
https://www.javaguides.net/2018/11/hibernate-save-an-entity-example.html

```


## 강의 기록 
```
# 4.hibernate-crud-example
> https://www.javaguides.net/2019/02/hibernate-crud-example.html

[hibernate CURD method]
CRUD operations are Create(save), Read(select), Update(update) and Delete(delete). 
Hibernate has Session interface which provides many APIs to perform operations with database.
(hibernate Session 인터페이스에 CRUD 관련 메소드가 존재한다는 걸 설명함)

Here are below Session interface methods we will use to develop CRUD operations with an example.
*save(Object object) Method - save() method persist the given transient instance, first assigning a generated identifier. (Or using the current value of the identifier property if the assigned generator is used.) This operation cascades to associated instances if the association is mapped with cascade="save-update".
*saveOrUpdate(Object object) Method - This method either save(Object) or update(Object) the given instance, depending upon the resolution of the unsaved-value checks (see the manual for a discussion of unsaved-value checking). 
*Session.delete(Object object) Method - Remove a persistent instance from the datastore.
*Session.get() - This method returns a persistence object of the given class with the given identifier. It will return null if there is no persistence object.

[Read an Entity]
*Session.get() → This method returns a persistence object of the given class with the given identifier. It will return null if there is no persistence object.
*Session.load() → This method returns a persistence object of the given class with the given identifier. It will throw an exception ObjectNotFoundException if an entity does not exist in the database. The load() method may return a proxy object instead of a real persistence object.
*Session.byId() → This method is used to obtain a persistence object by it a primary identifier.
Before snippets to read an entity from a database using Session.get(), Session.load() and Session.byId() 

[Update an Entity]
> Session에 saveOrUpdate() method 사용한다함 

[Delete or Remove an Entity]
> Session에 delete() , remove() 있다함 



# 5. hibernate-First Level Cache with Example 
> https://www.javaguides.net/2019/12/hibernate-first-level-cache-with-example.html
> 참고 사이트 
https://velog.io/@dnjscksdn98/JPA-Hibernate-First-Level-Cache-Second-Level-Cache
> 메모 
  - 1차 캐쉬에 저장 되고 동일한 object 요청시 1차 캐쉬 리턴되고 삭제됨 
  - 1차 캐쉬 값을 호출하면 사라지기 때문에 3번재 요청의 경우 쿼리가 출력이 됨 (session 에 특정 매소드 통해 캐쉬 날려버릴 수 있는 듯)
  - 2차 캐쉬의 경우 application에 저장되는 형태
  

# 8. Hibernate Query Language Tutorial (HQL)
> Clause(절, 클러스)

※ insert into .. select 문 정상 동작 안함 
> 확인했을때 지원하지 않는 듯 .. 그래서 session.save() 를 사용하거나 createNativeQuery().setParameter().executeUpdate() 형식으로 해서 할 수 있는 듯 
https://stackoverflow.com/questions/32653692/how-to-write-hql-insert-query

> 메모
  - hql 에서는 INSERT INTO 문을 지원하지 않음 ( 그냥 INSERT 빨간줄 나는데로 실행해도 되는데?)
    >> 그래서 session.save() 를 사용하거나 createNativeQuery().setParameter().executeUpdate() 형식으로 해서 처리가능 

# 11. One to One Unidirectional(단방향) Mapping Annotation Example
> https://www.javaguides.net/2019/08/hibernate-5-one-to-one-mapping-annotation-example.html
> one to one join 을 어떻게 하느냐에 따라 단순히 외래키만 참조하는 방식으로 할 수 있고, 또는 중간 테이블 하나 생성해서 하는 방식이 있는 듯 (영상은 실제 예제와 다름)
  >> JoinTable, ForeignKey, sharedPrimaryKey 새가지 종류를 설명하는데.. hibernate 설정도 xml로 하고 *utils 내용도 다르고 ..
  >> 1. Using foreign key associtation
  >> 2. Using common join table 
  >> 3. Using shared primary key 
  https://www.youtube.com/watch?v=LTdMKhHlQyg&list=PLGRDMO4rOGcMrHnQoSg3pK4PpxCV6pzmO&index=7
> Application.OneToOneTest() 실행시 
  >> 우선 InstructorDetail insert 후 id값 받아와서 Instructor insert 함 (외래키가 잡혀있으니.. 당연한 절차인듯)
  >> (??) InstructorDetailDao 만들지도 않았는데 동작하네 ;;

※ What is the EntityManager?
The EntityManager API is used to access a database in a particular unit of work. 
It is used to create and remove persistent entity instances, 
to find entities by their primary key identity, and to query over all entities. 
This interface is similar to the Session in Hibernate. **    // hiberante의 session 인터페이스는 jpa 의 EntityManager와 유사하다 **


```

###### 참고 
[https://victorydntmd.tistory.com/195?category=795879](https://victorydntmd.tistory.com/195?category=795879 'JPA와 hibernate')
[https://34codefactory.medium.com/bootstrapping-hibernate-5-with-spring-code-factory-367a87d35630](https://34codefactory.medium.com/bootstrapping-hibernate-5-with-spring-code-factory-367a87d35630 'Bootstrapping Hibernate 5 with Spring')