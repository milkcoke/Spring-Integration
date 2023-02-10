package falcon.springpractice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class AroundHubExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(CustomException e) {
        HttpHeaders httpHeaders = new HttpHeaders();

        log.error("Error occurs");

        Map<String, String> entityMap = new HashMap<>(3, 1.0f) {{
            put("code", e.getCode() + "");
            put("message", e.getMessage());
            put("cause", e.getClass().getSimpleName());
        }};

        return new ResponseEntity<>(entityMap, httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
