package falcon.springpractice.beans;

public class S3Registry implements Registry {
  @Override
  public void print() {
    System.out.println("S3 Registry");
  }
}
