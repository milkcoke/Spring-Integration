package falcon.springpractice.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomErrorCodeTest {

    @DisplayName("Enum test")
    @Test
    void iterateEnumTest() {
        for (var value : CustomErrorCode.values()) {
            System.out.println(value.getCode());
        }
    }

}