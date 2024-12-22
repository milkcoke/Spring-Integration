package annotation.config;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
//    which class is used to configuration.
        classes = ApplicationContextTestResourceNameType.class
)
public class FieldResourceInjectionIntegrationTest {

    @Resource(name = "namedFile")
    private File defaultFile;

    @Resource
    private File typedFile;

//    Injection of resource dependencies failed.
//    NoUniqueBeanDefinitionException expected

    @Resource
    private File typed2File;

    // @Resource 적용순서 (name > type > qualifier)
    @Resource
    @Qualifier("special")
    private File specialFile;

//    @Inject
//    private File injectedFile;

    @DisplayName("Resource Matched by name")
    @Test
    void fileNameCheckTestByConfiguration() {
        assertNotNull(defaultFile);
        assertThat("namedFile.txt").isEqualTo(defaultFile.getName());
    }

    @DisplayName("Resource Matched by type")
    @Test
    void fileNameByType() {
        assertNotNull(typedFile);
        assertThat("typedFile.txt").isEqualTo(typedFile.getName());
    }

    @DisplayName("Resource Matched by type2")
    @Test
    void fileNameByType2() {
        assertNotNull(typed2File);
        assertThat("typed2File.txt").isEqualTo(typed2File.getName());
    }

    @DisplayName("Resource Matched by qualifier")
    @Test
    void fileNameByQualifier() {
        assertNotNull(specialFile);
        assertThat("specialFile.txt").isEqualTo(specialFile.getName());
    }

}
