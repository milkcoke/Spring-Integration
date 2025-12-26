package spring.core.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(MessageServiceRegistrar.class)
@Configuration
public class MessageConfig {

}
