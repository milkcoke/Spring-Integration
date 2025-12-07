package spring.core.singleton;

public class StatelessService {
//    private int price; // 상태를 유지 삭제

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
