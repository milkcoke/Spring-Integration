# Spring-Annotation-Practice
Spring annotation related practice repository

## Build & Deploy

```bash
$ cd [PROJECT-DIRECTORY]
$ ./gradlew build
$ cd build/libs
$ java -jar [filename].jar
```


## Dependency Injection Annotations

- `@Resource`
- `@Qualifier`
- `@Autowired`
- `@Inject`

### @Resource
프로세스는 name -> type -> qualifier 순으로 탐색.

#### Name
@Resource(name = "[BEAN-NAME]") 등록된 이름으로 매칭시킴.

#### Type
@Resource 어노테이션만 붙이면 끝남. \
bean name 명시하지 않으면 타입을 체킹함. **이때 주입받는 field 명이 메소드명과 일치해야함** \
Bean 어노테이션의 기본 네이밍은 **Method-Level** 로, 메소드명과 같이 등록하게됨. \
참조: [Spring resource annotation](https://www.baeldung.com/spring-annotations-resource-inject-autowire)

```java
@Configuration
public class ApplicationContextTestResourceNameType {
    @Bean(name = "namedFile")
    public File namedFile() {
        return new File("namedFile.txt");
    }
    // 빈 이름을 생략해도 메소드 이름인 `typedFile` 로 빈 이름이 지정됨.
    @Bean
    public File typedFile() {
        return new File("typedFile.txt");
    }
}
```

### Qualifier

```java
public class ApplicationContextTestResourceNameType {
    // Bean 으로 하든지
    @Bean(name = "specialA")
    public File specialAA() {
        return new File("specialFileAA.txt");
    }
    // Qualifier 로 하든지
    @Qualifier("specialF")
    public File specialFF() {
        return new File("specialFileFF.txt");
    }
}
```

#### Example of combination
```java
public class ApplicationContextTestResourceNameType {
    @Bean(name = "aaa")
    @Qualifier(name = "bbb")
    public File specialF() {
        return new File("specialF");
    }
}
```

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = ApplicationContextTestResourceNameType.class
)
public class FieldResourceInjectionIntegrationTest {
    @Resource
    @Qualifier("bbb") // 주입 성공.
    private File specialFile;
}
```

### Autowired

### @Inject
This is independent on Spring F/W
You should add build.gradle
```build.gradle
dependencies {
    implementation 'jakarta.inject:jakarta.inject-api:2.0.1'
}
```

### Annotation Summary
| Index      | Supported by | Apply process             | When to use                                                                    |
|------------|--------------|---------------------------|--------------------------------------------------------------------------------|
| @Resource  | Java         | name -> type -> qualifier | DI independent on Spring F/W, and **support only field, setter injection**     |
| @Qualifier | Spring F/W   |                           | multiple bean, same type, special condition name                               |
| @Autowired | Spring F/W   | type -> name -> qualifier | DI should be executed Spring F/W, support constructor, field, setter injection |                             
| @Inject    | Java         | type -> qualifier -> name | DI independent on Spring F/W, constructor, field, setter, normal injection     |


`@Autowired` 와 `@Inject` 는 Spring 프레임워크 의존 여부에 따라서 \
`@Qaulifier` 는 같은 타입의 여러 빈이 등록 되어있을 때 어느 빈을 주입해야 할 지 특정할 필요가 있을 때 사용할 수 있다.


