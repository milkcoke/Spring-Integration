package falcon.springpractice.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {

    INVALID_ARGUMENT(1004_4000),
    MISSING_REQUIRED_ARGUMENT(1004_4001),
    TOO_MANY_REQUEST(1004_4240);

    private final int code;
    CustomErrorCode(int code) {
        this.code = code;
    }
}
