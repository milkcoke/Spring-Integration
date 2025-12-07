package spring.core.web.log;

import lombok.RequiredArgsConstructor;
import spring.core.common.MyLogger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService implements LogService {
    private final MyLogger myLogger;

    @Override
    public void logging(String testId) {
        myLogger.logging("service ID : " + testId);
    }
}
