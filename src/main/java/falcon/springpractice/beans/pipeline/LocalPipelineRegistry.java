package falcon.springpractice.beans.pipeline;

import org.springframework.stereotype.Component;

@Component("local")
public class LocalPipelineRegistry implements PipelineRegistry {
  @Override
  public void print() {
    System.out.println(this.getClass().getSimpleName());;
  }
}
