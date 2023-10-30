package com.springyamlconfig.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("")
class AppConfig {
    lateinit var name: String
}