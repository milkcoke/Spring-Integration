package falcon.springpractice.beans.schema;

import org.springframework.stereotype.Component;

@Component("s3")
public class S3SchemaRegistry implements SchemaRegistry {
  @Override
  public void print() {
    System.out.println("S3 Schema Registry");
  }
}
