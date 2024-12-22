package falcon.springpractice.beans.schema;

import org.springframework.stereotype.Component;

@Component("local")
public class LocalSchemaRegistry implements SchemaRegistry {
  @Override
  public void print() {
    System.out.println("Local Schema Registry");
  }
}
