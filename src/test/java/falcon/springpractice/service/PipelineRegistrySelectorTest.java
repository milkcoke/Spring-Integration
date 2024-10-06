package falcon.springpractice.service;

import falcon.springpractice.beans.pipeline.LocalPipelineRegistry;
import falcon.springpractice.beans.pipeline.PipelineRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PipelineRegistrySelectorTest {

  @Autowired
  private PipelineRegistrySelector registrySelector;

  @DisplayName("Pipeline Schema Registry")
  @Test
  void getRegistry() {
    // given
    String registryName = "local";

    // when then
    assertThat(registrySelector.getRegistry(registryName)).isInstanceOf(LocalPipelineRegistry.class);
  }

}
