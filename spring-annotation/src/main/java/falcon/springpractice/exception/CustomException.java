package falcon.springpractice.exception;

import lombok.Getter;

// Class 는 하나로 만들고
// code 와 message 를 강제한다.
@Getter
public class CustomException extends RuntimeException {
    private final int code;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.name());
        this.code = customErrorCode.getCode();
    }
}
