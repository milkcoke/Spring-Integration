package falcon.springpractice.beans.schema;

import org.springframework.stereotype.Component;

@Component("db")
public class DBSchemaRegistry implements SchemaRegistry {
  @Override
  public void print() {
    System.out.println("DB Schema Registry");
  }
}
