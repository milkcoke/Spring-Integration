package com.springyamlconfig

import com.springyamlconfig.config.AppConfig
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringYamlConfigApplication(
    private val appConfig: AppConfig
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
//    init {
//        this.printAppName()
//    }
//    private fun printAppName() {
//        logger.info(this.appConfig.name)
//    }
}

fun main(args: Array<String>) {
    runApplication<SpringYamlConfigApplication>(*args)
}
