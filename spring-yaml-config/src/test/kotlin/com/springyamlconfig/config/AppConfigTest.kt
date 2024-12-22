package com.springyamlconfig.config

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
class AppConfigTest {
    @Autowired
    private lateinit var appConfig: AppConfig

    @DisplayName("Nested config")
    @Test
    fun appConfigPropertiesTest() {
        //given
        val champ = appConfig.champ
        val person = appConfig.person
        //when then
        assertThat(champ.hiddenName).isEqualTo("vladimir")
        assertThat(champ.exposeName).isEqualTo("khazix")
        assertThat(person.name).isEqualTo("falcon")
        assertThat(person.age).isEqualTo(55)
    }
}