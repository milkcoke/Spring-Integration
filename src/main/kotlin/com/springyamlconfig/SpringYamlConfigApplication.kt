package com.springyamlconfig

import com.springyamlconfig.config.AppConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringYamlConfigApplication(
    private val appConfig: AppConfig
) {
    init {
        this.printAppName()
    }
    private fun printAppName() {
        println(this.appConfig.name)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringYamlConfigApplication>(*args)
}
