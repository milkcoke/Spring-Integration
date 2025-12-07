package spring.core.config;

import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer
                .addSupportedVersions("1.0.0", "2.0.0")
                .setDefaultVersion("1.0.0")
                .usePathSegment(1)
                .setVersionParser(new ApiVersionConfig());
    }
}
