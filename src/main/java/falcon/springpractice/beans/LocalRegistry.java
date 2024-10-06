package falcon.springpractice.beans;

public class LocalRegistry implements Registry {
  @Override
  public void print() {
    System.out.println("LocalRegistry");
  }
}
