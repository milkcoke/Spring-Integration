package falcon.springpractice.beans;

public class DBRegistry implements Registry {
  @Override
  public void print() {
    System.out.println("DB Registry");
  }
}
