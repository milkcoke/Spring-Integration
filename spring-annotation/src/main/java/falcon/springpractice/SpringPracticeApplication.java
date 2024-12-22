package falcon.springpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPracticeApplication {

	public static void main(String[] args) {
		// 자신의 class 를 넣어버림..
		// Annotation 'SpringBootApplication' 에 의해
		// 톰캣이라는 웹서버를 내장한 놈인데, 걔를 자체적으로 띄워버림. (run)
		SpringApplication.run(SpringPracticeApplication.class, args);
	}

}
