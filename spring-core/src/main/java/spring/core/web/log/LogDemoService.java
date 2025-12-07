package spring.core.web.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService implements LogService {
  private final MyLogger myLogger;

  @Override
  public void logging(String testId) {
    myLogger.logging("service ID : " + testId);
  }
}
