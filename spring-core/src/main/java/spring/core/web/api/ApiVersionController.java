package spring.core.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiVersionController {

    @GetMapping(value = "/{version}/version", version = "v1")
    public String versionV1() {
        return "v1";
    }

    @GetMapping(value = "/{version}/version", version = "v2")
    public String versionV2() {
        return "v2";
    }
}
