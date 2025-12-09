package spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.registry.ImportHttpServices;
import spring.core.service.todo.NewerTodoService;

/**
 * By @ImportHttpServices, Spring will automatically create and register <br>
 * the NewerTodoService bean in the application context.
 * You don't need to manually define a @Bean method for it.
 */
@Configuration(proxyBeanMethods = false)
@ImportHttpServices(NewerTodoService.class)
public class TodoConfig {

//  @Bean
//  public NewerTodoService todoService(RestClient.Builder builder) {
//    RestClient client = builder
//      .baseUrl("https://jsonplaceholder.typicode.com")
//      .build();
//
//    RestClientAdapter adapter = RestClientAdapter.create(client);
//    HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//    return proxyFactory.createClient(NewerTodoService.class);
//  }
}
