
## BeanDefinition
.xml 이나 config.java 가 아닌 직접 Bean Definition 을 동적으로 생성하여 스프링 컨테이너에 등록하여 사용할 수도 있으나 \
이런 경우는 거의 없다.

스프링이 다양한 형태의 설정 정보를 (`.xml`, `.java` etc..) Bean Definition 이라는 인터페이스를 의존하여 
메타정보를 가지고 스프링 빈을 생성한다는 점만 기억하면 된다.

### BeanDefinition 정보
- BeanClassName \
생성할 빈의 클래스 이름
- factoryBeanName \
팩토리 역할의 빈을 사용할 경우 이름 ex) appConfig
- factoryNameMethod \
빈을 생성할 팩토리 메서드 이름 ex) memberService
- Scope: 싱글톤 (Default)
- lazyinit \
스프링 컨테이너 생성시 빈이 아니라, 실제 빈을 사용할 때까지 최대한 생성을 지연할지에 대한 처리 여부
- initMethodName \
빈을 생성하고 의존관계 적용 후 호출되는 초기화 메소드 명
- DestroyMethodName \
빈의 생명주기 종료 직전에 호출될 메소드 명
- Constructor arguments, Properties \
의존관계 주입에서 사용.

![beanDefinition Example](https://github.com/milkcoke/Spring-Core/src/main/resources/beanDefinition_test_log.png)