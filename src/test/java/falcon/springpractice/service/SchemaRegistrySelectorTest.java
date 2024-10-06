package falcon.springpractice.service;

import falcon.springpractice.beans.schema.LocalSchemaRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SchemaRegistrySelectorTest {

  @Autowired
  private SchemaRegistrySelector registrySelector;

  @DisplayName("print Registry")
  @Test
  void getRegistry() {
    // given
    String registryName = "local";

    // when then
    assertThat(registrySelector.getRegistry(registryName)).isInstanceOf(LocalSchemaRegistry.class);
  }
}
