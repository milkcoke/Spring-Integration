package annotation.config;

import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    loader=AnnotationConfigContextLoader.class,
    classes= ArbitraryDependency.class
)
class ArbitraryDependencyTest {

    @Inject
    private ArbitraryDependency fieldInjectDependency;

    @DisplayName("class inject test")
    @Test
    void giveInjectAnnotation() {
        assertNotNull(fieldInjectDependency);
        assertThat("Arbitrary Dependency").isEqualTo(fieldInjectDependency.toString());
    }
}
