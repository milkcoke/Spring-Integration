package falcon.springpractice.controller;

import falcon.springpractice.exception.CustomErrorCode;
import falcon.springpractice.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {
    @GetMapping("exception")
    public ResponseEntity getException(){
        throw new CustomException(CustomErrorCode.INVALID_ARGUMENT);
    }
}
