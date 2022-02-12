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

[에러] HHH000206: hibernate.properties not found
> 참고 : https://www.inflearn.com/questions/13985
>> jaxb 관련 라이브러리가 필요한가?

# Difference Between Hibernate and Spring Data JPA 
> https://www.javaguides.net/2018/12/what-is-difference-between-hibernate-and-spring-data-jpa.html
> hibernate 와 spring data jpa 의 차이 
  Hibernate is a JPA implementation, while Spring Data JPA is a JPA Data Access Abstraction. 
  Spring Data offers a solution to GenericDao custom implementations. 
  It can also generate JPA queries on your behalf through method name conventions.

```

###### 참고 
[https://victorydntmd.tistory.com/195?category=795879](https://victorydntmd.tistory.com/195?category=795879 'JPA와 hibernate')
[https://34codefactory.medium.com/bootstrapping-hibernate-5-with-spring-code-factory-367a87d35630](https://34codefactory.medium.com/bootstrapping-hibernate-5-with-spring-code-factory-367a87d35630 'Bootstrapping Hibernate 5 with Spring')

## hiberante repository 
```
#1. select(1) 단순
        
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Board> query = cb.createQuery(Board.class);
            Root<Board> root = query.from(Board.class);
            cq.select(root).where(cb.equal(root.get("useYn"),"Y"));
            List<Board> list = session.createQuery(cq).getResultList();

        session.close();

#2. select(2) 

        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Board> query = cb.createQuery(Board.class);
            Root<Board> root = query.from(Board.class);
            
            root.fetch("user"); // fetch join 

            query.select(root).where(cb.equal(root.get("useYn"),"Y"));

        final TypedQuery<Board> typedQuery = session.createQuery(query);
        typedQuery.setFirstResult(0).setMaxResults(10); // (page-1)*pagesize
        final List<Board> resultList = typedQuery.getResultList();

        session.close();

#3. select(3) hql 사용할 경우
        Session session = sessionFactory.openSession();

        String hql = " SELECT distinct b " +
                     "  FROM Board b join fetch b.user " +
                     " WHERE b.useYn = 'Y' " +
                     "  and ( b.title like :keyword or b.content like :keyword ) ";

        Query<Board> query= session.createQuery(hql, Board.class);
        query.setParameter("keyword","%"+keyword+"%");
        List<Board> list = query.getResultList();

        session.close();

# 4. select(4) 진짜 될 줄이야.

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Board> query = cb.createQuery(Board.class);
            Root<Board> root = query.from(Board.class);

        root.fetch("user");
        // 이게 cb.and 하면 equal 다음에 and가 붙네
        query.select(root)
                        .where(
                                cb.and(cb.equal(root.get("useYn"),"Y")),
                                cb.or(
                                        cb.like(root.get("title"),"%"+boardSearchDTO.getKeyword()+"%")
                                        ,cb.like(root.get("content"),"%"+boardSearchDTO.getKeyword()+"%")
                                )
                        );

        query.orderBy(cb.desc(root.get("id")));

        final TypedQuery<Board> typedQuery = session.createQuery(query);

        typedQuery.setFirstResult((boardSearchDTO.getPage()-1) * boardSearchDTO.getSize())
                  .setMaxResults(boardSearchDTO.getSize());

        final List<Board> list = typedQuery.getResultList();

        return list;

select
        board0_.id as id1_0_0_,
        user1_.id as id1_2_1_,
        board0_.created_date as created_2_0_0_,
        board0_.modified_date as modified3_0_0_,
        board0_.content as content4_0_0_,
        board0_.title as title5_0_0_,
        board0_.use_yn as use_yn6_0_0_,
        board0_.user_id as user_id8_0_0_,
        board0_.view_cnt as view_cnt7_0_0_,
        user1_.created_date as created_2_2_1_,
        user1_.modified_date as modified3_2_1_,
        user1_.authority as authorit4_2_1_,
        user1_.email as email5_2_1_,
        user1_.nickname as nickname6_2_1_,
        user1_.password as password7_2_1_,
        user1_.username as username8_2_1_ 
    from
        Board board0_ 
    inner join
        User user1_ 
            on board0_.user_id=user1_.id 
    where
        board0_.use_yn=? 
        and (
            board0_.title like ? 
            or board0_.content like ?
        ) 
    order by
        board0_.id desc limit ?

# 5. 게시판 count 
(수정전)
        Long result = 0L;
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Board> root = cq.from(Board.class);
            Query<Long> query = session.createQuery(cq.select(cb.countDistinct(root))
                                                      .where(cb.equal(root.get("useYn"),"Y")));
            result = query.getSingleResult();

        session.close();

# 6. 쿼리를 조금더 깔끔하게 

        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Board> query = cb.createQuery(Board.class);
            Root<Board> root = query.from(Board.class);

            root.fetch("user");

            query.select(root)
                 .where(
                        cb.and(cb.equal(root.get("useYn"),"Y")),
                        cb.or(
                                cb.like(root.get("title"),"%"+searchDTO.getKeyword()+"%")
                                ,cb.like(root.get("content"),"%"+searchDTO.getKeyword()+"%")
                        )
                 )
                 .orderBy(cb.desc(root.get("id")));
        (수정전)
            final TypedQuery<Board> typedQuery = session.createQuery(query);

            typedQuery.setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize())
                      .setMaxResults(searchDTO.getSize());

            final List<Board> list = typedQuery.getResultList();
        (수정후)
             final List<Board> list = session.createQuery(query)
                                            .setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize())
                                            .setMaxResults(searchDTO.getSize())
                                            .getResultList();

        session.close();

        return list;


# 게시판 조회 백업
public Long totalCount(SearchDTO searchDTO){

        Long result;
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Board> root = query.from(Board.class);

            query.select(cb.countDistinct(root))
                 .where(
                         cb.and(cb.equal(root.get("useYn"),"Y")),
                         cb.or(
                                 cb.like(root.get("title"),"%"+searchDTO.getKeyword()+"%")
                                ,cb.like(root.get("content"),"%"+searchDTO.getKeyword()+"%")
                         )
                 );

            result = session.createQuery(query).getSingleResult();

        session.close();
        return result;
    }

    public List<Board> findList(SearchDTO searchDTO){

        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Board> query = cb.createQuery(Board.class);
            Root<Board> root = query.from(Board.class);

            root.fetch("user");

            query.select(root)
                 .where(
                        cb.and(cb.equal(root.get("useYn"),"Y")),
                        cb.or(
                                cb.like(root.get("title"),"%"+searchDTO.getKeyword()+"%")
                                ,cb.like(root.get("content"),"%"+searchDTO.getKeyword()+"%")
                        )
                 )
                 .orderBy(cb.desc(root.get("id")));

            List<Board> list = session.createQuery(query)
                                      .setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize())
                                      .setMaxResults(searchDTO.getSize())
                                      .getResultList();

        session.close();
        return list;
    }

# 댓글 조회 백업 
ublic Long totalCount(Long id){

        Long result;
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Comment> root = query.from(Comment.class);

            query.select(cb.countDistinct(root))
                            .where(cb.equal(root.get("board"), id));

            result = session.createQuery(query).getSingleResult();

        session.close();
        return result;

    }

    public List<Comment> findList(Long id, SearchDTO searchDTO){

        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Comment> query = cb.createQuery(Comment.class);
            Root<Comment> root = query.from(Comment.class);

            root.fetch("board");
            root.fetch("user");

            query.select(root)
                  .where(cb.equal(root.get("board"), id))
                  .orderBy(cb.desc(root.get("parent_no")), cb.asc(root.get("sort_order")));

            List<Comment> list = session.createQuery(query)
                                        .setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize())
                                        .setMaxResults(searchDTO.getSize())
                                        .getResultList();

        session.close();
        return list;
    }

# 게시판 hql 백업
public Long totalCount(SearchDTO searchDTO){

        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT count(b) " +
                     "FROM Board b " +
                     "WHERE b.useYn = 'Y' " +
                     "  and ( b.title like :keyword or b.content like :keyword ) ";

        Query<Long> query= session.createQuery(hql, Long.class);
        query.setParameter("keyword","%"+searchDTO.getKeyword()+"%");

        return query.getSingleResult();
    }

    public List<Board> findList(SearchDTO searchDTO){

        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT b " +
                     "FROM Board b join fetch b.user " +
                     "WHERE b.useYn = 'Y' " +
                     "  and ( b.title like :keyword or b.content like :keyword )" +
                     "ORDER BY b.id desc " ;

        Query<Board> query= session.createQuery(hql, Board.class)
                                    .setParameter("keyword","%"+searchDTO.getKeyword()+"%")
                                    .setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize())
                                    .setMaxResults(searchDTO.getSize());

        return query.getResultList();
    }
# 댓글 hql 백업 
public Long totalCount(Long id){
        Session session = sessionFactory.getCurrentSession();

            String hql = "SELECT count(c) " +
                         "FROM Comment c " +
                         "WHERE c.board.id = :id ";

            Query<Long> query = session.createQuery(hql,Long.class);
            query.setParameter("id" , id);

        return query.getSingleResult();
    }

    public List<Comment> findList(Long id, SearchDTO searchDTO){

        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT distinct c " +
                     "FROM Comment c join fetch c.user " +
                     "WHERE c.board.id = :id and c.useYn = 'Y' " +
                     "ORDER BY c.parent_no desc, c.sort_order asc ";

        Query<Comment> query = session.createQuery(hql, Comment.class)
                                      .setParameter("id", id)
                                      .setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize())
                                      .setMaxResults(searchDTO.getSize());

        return query.getResultList();
    }

```