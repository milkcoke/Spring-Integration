package milkcoke.core.domain.order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Order {

    private final Long memberId;
    private final String itemName;
    private final int itemPrice;
    private final int discountPrice;

    /**
     * @return 할인된 금액
     */
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }

}
