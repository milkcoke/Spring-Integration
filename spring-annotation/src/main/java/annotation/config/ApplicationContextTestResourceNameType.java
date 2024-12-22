package annotation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
// Bean 등록은 Configuration annotation 붙은 곳에서만 가능.
// 각 메소드의 리턴 객체가 스프링 빈 객체임을 선언.
public class ApplicationContextTestResourceNameType {
    @Bean(name = "namedFile")
    public File namedFile() {
        return new File("namedFile.txt");
    }

    // If bean name is omitted, method name is registered as bean name.
    // Refer to : https://www.baeldung.com/spring-bean-names
    @Bean
    public File typedFile() {
        return new File("typedFile.txt");
    }

    @Bean
    public File typed2File() {
        return new File("typed2File.txt");
    }


    @Bean(value = "special2")
    @Qualifier(value = "special")
    public File specialFF() {
        return new File("specialFile.txt");
    }

    @Bean("injectedF")
    public File injectFile() {
        return new File("injectFile.txt");
    }
}
