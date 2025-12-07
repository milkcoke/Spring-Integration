# Spring Core
스프링 핵심 원리를 파헤쳐보자!

## SpringBoot 4.0

#### Java Version
SpringBoot 4.x requires Java 17 or later.

#### Virtual thread
Autoconfigured HTTP clients backed by the JDK HttpClient are now configured to use virtual threading \
when `spring.threads.virtual.enabled` is true.

#### Jackson version
SpringBoot 4.x uses jackson 3.x as default , 2.x supported as deprecated form.

#### API versioning

```java
@RestController
@RequestMapping("/api")
public class ApiVersionController {
    @GetMapping(value = "/{version}/version", version = "v1")
    public String versionV1() {
        return "v1";
    }
    @GetMapping(value = "/{version}/version", version = "v2")
    public String versionV2() {
        return "v2";
    }
}

```

#### (1) Configuration by `application.yaml`
```yaml
spring:
  mvc:
    apiversion:
      supported:
        - v1
        - v2
      default: v1
      use:
        # The value you set for path-segment is the index of the path element after the host and port.
        path-segment: 1
```
#### (2) Configuration by class
```java
public class ApiVersionConfig implements ApiVersionParser {
    @Override
    public Comparable parseVersion(String version) {
        version = version.toLowerCase();
        version = version.substring(1);
        return version;
    }
}


@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer
                .addSupportedVersions("v1", "v2")
                .setDefaultVersion("v1")
                .usePathSegment(1)
                .setVersionParser(new ApiVersionConfig());
    }
}
```

#### Why `path-segument` is required?
Since the URL is just a string of segments separated by slashes, Spring needs a specific rule to find the version. The integer value of path-segment provides this rule: it specifies the zero-based index of the segment that holds the version value. \
Example: If your URL is `https://example.com/api/v2/products` and you set spring.mvc.apiversion.use.path-segment=1:

Spring splits the path: ["api", "v2", "products"]. 

It looks at index 1, extracts "v2", and then uses this extracted value to find the method annotated with version = "v2".

> ⚠️Without this setting, Spring sees a request URL `(e.g., /api/v1/users)` and an annotation rule (e.g., `@GetMapping(version="v1")`), but it doesn't know where to look in the URL for the "v1" value.


---
## Configuration


## Spring Container 등록

```java
@Configuration
@ComponentScan(
        // 패키지를 찾을 시작 위치를 지정할 수 있음.
        basePackages = "milkcoke.core",
        // Configuration 붙은 클래스는 제외 (ex. config.spring.core.AppConfig)하여 충돌 방지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class config.spring.core.AutoAppConfig {
    // DI 를 생성자 없이 어떻게 할 것인가? => AutoWired!

}

```

### Configurations
아래 옵션이 지정되지 않은 경우 \
`@ComponentScan` 을 붙인 해당 패키지를 시작으로 예하 모든 패키지를 스캔한다.

> 위 메커니즘에 따라 **설정 정보 클래스 위치를 프로젝트 최상단에 두는 것이 관례가 되었다.**

- `basePackages` \
탐색할 패키지의 시작 위치 지정, 본 패키지를 포함한 모든 하위 패키지 탐색 \
여러 시작 위치를 지정할 수도 있음.
- `basePackageClasses` \
지정한 클래스의 패키지를 탐색 시작 위치로 지정


### 빈 이름 중복 충돌 처리
> 📝 Bean 이름은 항상 Unique 하도록 보장하라.

Spring bean 이름에 중복이 발생한 경우
> "Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true"

 수동 등록 빈이 자동 등록 빈에 비해 우선권을 가지고 오버라이딩 해주나, 기본적으로 중복 발생시 SpringBoot App 실행시 오류가 발생하도록 한다. \
(빈 자동 vs 수동 등록 충돌은 개발자의 의도 밖에 있는 일인 경우가 매우 많기 때문이다.)



