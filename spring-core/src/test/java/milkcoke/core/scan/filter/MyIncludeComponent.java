package milkcoke.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // type: class 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // 얘가 붙은건 Component scan 에 추가할 것이다.
}
