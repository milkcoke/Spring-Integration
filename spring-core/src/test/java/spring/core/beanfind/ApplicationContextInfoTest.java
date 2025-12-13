package spring.core.beanfind;

import spring.core.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBeans() {
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            var bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean);
        }
    }


    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBeans() {
        // 스프링에 등록된 모든 빈 정보 출력
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            var beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION: 사용자가 정의한 빈
            // ROLE_INFRASTRUCTURE: Spring 내부에서 사용하는 빈
            if (beanDefinition.getRole() != BeanDefinition.ROLE_APPLICATION) continue;

            // 빈 정보 (객체) 조회
            var bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean);
        }
    }
}
