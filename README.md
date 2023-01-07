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

| Value       | Description                                                  |
|-------------|--------------------------------------------------------------|
| create      | 시작시 스키마를 재생성                                                 |
| create-drop | 어플리케이션 종료 시점에 생성했던 스키마 삭제                                    |
| update      | 시작시 Entity class 구성과 스키마를 비교하여 컬럼 추가/삭제 , 기존 스키마를 삭제하지 않고 유지 |
| validate    | 시작시 Entity class 구성과 스키마가 다르다면 예외를 발생시킴.                     |

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

