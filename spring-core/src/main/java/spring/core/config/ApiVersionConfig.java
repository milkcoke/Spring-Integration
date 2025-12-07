package spring.core.config;

import org.springframework.web.accept.ApiVersionParser;

public class ApiVersionConfig implements ApiVersionParser {
    @Override
    public Comparable parseVersion(String version) {
        version = version.toLowerCase();
        if (version.startsWith("v")) {
            version = version.substring(1);
        }

        if (!version.contains(".")) {
            version = version + ".0.0";
        }

        return version;
    }
}
