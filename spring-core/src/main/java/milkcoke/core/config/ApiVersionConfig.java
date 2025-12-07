package milkcoke.core.config;

public class ApiVersionConfig implements org.springframework.web.accept.ApiVersionParser {
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
