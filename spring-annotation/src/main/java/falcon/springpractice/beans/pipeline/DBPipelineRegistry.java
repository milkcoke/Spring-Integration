package falcon.springpractice.beans.pipeline;

import org.springframework.stereotype.Component;

@Component("db")
public class DBPipelineRegistry implements PipelineRegistry {
  @Override
  public void print() {
    System.out.println(this.getClass().getSimpleName());
  }

}
