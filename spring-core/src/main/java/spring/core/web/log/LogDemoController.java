package spring.core.web.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import spring.core.common.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController implements LogController {
    private final LogService logService;
    private final MyLogger myLogger;

    @RequestMapping("logging-demo")
    // View template 없이 문자열 그대로 응답
    @ResponseBody
    @Override
    public String loggingDemo(HttpServletRequest httpServletRequest) {
        var reqUrl = httpServletRequest.getRequestURI();
        myLogger.setRequestUrl(reqUrl);

        myLogger.logging("Controller test");
        logService.logging("testId");

        return "OK";
    }
}
