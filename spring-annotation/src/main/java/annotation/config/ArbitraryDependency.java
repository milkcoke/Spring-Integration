package annotation.config;

import org.springframework.stereotype.Component;

@Component
public class ArbitraryDependency {
    private final String label = "Arbitrary Dependency";

    public String toString() {
        return this.label;
    }
}
