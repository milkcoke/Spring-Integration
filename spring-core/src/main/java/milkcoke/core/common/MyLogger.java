package milkcoke.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
// This bean created uniquely when HTTP request occurs
// and destroyed the HTTP request finishing.
// CGBLIB 이라는 라이브러리로 가짜 프록시 객체를 만들어서 주입 (다형성 원리 이용)
// 가짜 프록시 객체는 실제 요청시 실제 빈을 요청하는 위임 로직을 포함함.
// Request scope 와는 전혀 관계 없고, 내부에 단순한 위임 로직만 있음 (사실상 코드 길이 줄이기 위한 옵션)
// Provider 든 Proxy 든 진짜 객체 조회를 필요한 시점까지 지연 처리 한다는 점이 핵심이다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void logging(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestUrl + "] " + message);
    }

    @PostConstruct
    public void init() {
        // 유일성 보장.
        // 로또 1등확률보다 낮음.
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean created: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]" + " request bean destroyed: " + this);
    }
}
