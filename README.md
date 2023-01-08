# JPA Practice
Don't need to set up SpringBoot. \
Understand the way of JPA how to operate.

## Hibernate configuration
Hibernate is implementation of JPA interface. 

You need to set up before entering project.

#### src/main/resources/META-INF/persistence.xml
```xml
<property name="hibernate.hbm2ddl.auto" value="validate" />
```

### hbm2dll.value option

| Value       | Description                                                  | When to use                                                |
|-------------|--------------------------------------------------------------|------------------------------------------------------------|
| create      | 시작시 스키마를 재생성                                                 | 개발 초기단계                                                    |
| create-drop | 어플리케이션 종료 시점에 생성했던 스키마 삭제                                    | 테스트 케이스를 실행하고 깔끔하게 모두 삭제하고 싶을 때                            |
| update      | 시작시 Entity class 구성과 스키마를 비교하여 컬럼 추가/삭제 , 기존 스키마를 삭제하지 않고 유지 | 개발 초기단계 또는 테스트 서버에서 변경된 스키마만 ALTER 로 반영하고 싶을 때 (운영에는 사용 X) |
| validate    | 시작시 Entity class 구성과 스키마가 다르다면 예외를 발생시킴.                     | Entity class 정의와 테이블이 정확히 일치하는지만 미리 확인 할 때                 |
| none        | 사용하지 않음                                                      | 관례상 위 옵션을 사용하지 않을 때 명시                                     |  

> 📝 staging 및 production 에는 `validate`, `none` 을 제외하고 절대 쓰지 마라.
> 복구가 불가능하다.
> **그냥 모르겠으면 안전하게 `validate`, `none` 을 써라.**

### DB DDL Tip 
- DDL 은 스크립트를 직접 작성해서 테스트 DB 에 먼저 테스트 한 후 반영하라. \
자동으로 툴이 생성해주는 DDL 에는 위험이 존재한다.
- ALTER, DROP 같은 DDL 은 애초에 개발자가 쓰지 못하게 계정 단위로 잠궈놓는다.

## Entity - Table mapping guide

### Mapping annotations
| Index       | Description                                         |
|-------------|-----------------------------------------------------|
| @Column     | Column                                              |
| @Comment    | Comments on table, column ..                        |
| @Temporal   | Date/Time/Timestamp                                 |
| @Enumerated | enum type                                           |
| @Lob        | BLOB, CLOB                                          |
| Transient   | Not mapping to DB, just use in application instance |

### Column annotations
| Index            | Description                          |
|------------------|--------------------------------------|
| name             | column name                          |
| nullable         | NULL constraint                      |
| unique           | Unique constraint                    |
| columnDefinition | Input column info using sql syntax   |
| length           | Length of varchar, varchar2, etc..   |
| precision scale  | Use in BigDecimal or BigInteger type |


### Entity class example
#### Member.java
```java
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    // javax 또는 jakarta 가 JPA 표준 인터페이스
    // Hibernate 은 구현체.

    // JPA 에서는 데이터 변경시 항상 Transaction 안에서 작업해야한다.
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "member_name")
    private String name;
    private Integer  age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Comment("최종 수정 시각")
    private Date lastModifiedDate;

    @Lob
    @Comment("비고 설명")
    private String description;
}
```
---