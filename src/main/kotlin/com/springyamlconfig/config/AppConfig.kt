package com.springyamlconfig.config

import com.springyamlconfig.Champ
import com.springyamlconfig.Person
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app")
data class AppConfig (
    val person: Person,
    val champ: Champ
)