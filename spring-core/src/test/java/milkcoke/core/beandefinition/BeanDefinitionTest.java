package milkcoke.core.beandefinition;

import milkcoke.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            var beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() != BeanDefinition.ROLE_APPLICATION) continue;
            System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
        }
    }
}
