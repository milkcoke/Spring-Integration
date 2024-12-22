package milkcoke.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    // 애플리케이션 시작시 특정 URL 에 연결
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("Connected to : " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    // 애플리케이션 종료 전 호출
    public void disconnect() {
        System.out.println("Connection closed of " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("After bean created and DI");
        connect();
        call("Initialize connection");
    }

    @PreDestroy
    public void close() {
        System.out.println("Before close() method be called, call `destroy()` callback method.");
        disconnect();
    }
    /*
    // 의존관계 주입이 끝나면 실행하겠다는 뜻
    @Override
    public void afterPropertiesSet() {
        System.out.println("After bean created and DI");
        connect();
        call("Initialize connection");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Before close() method be called, call `destroy()` callback method.");
        disconnect();
    }
     */
}
