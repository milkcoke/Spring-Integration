package spring.core.web.log;

import jakarta.servlet.http.HttpServletRequest;

public interface LogController {
  String loggingDemo(HttpServletRequest httpServletRequest);
}
