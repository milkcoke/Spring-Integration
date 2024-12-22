package falcon.springpractice.beans.pipeline;

import org.springframework.stereotype.Component;

@Component("s3")
public class S3SchemaRegistry implements PipelineRegistry {
  @Override
  public void print() {
    System.out.println(this.getClass().getSimpleName());
  }
}
