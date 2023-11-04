package com.springyamlconfig.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("")
data class AppConfig (
    val name: String
)