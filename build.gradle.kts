import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("com.google.cloud.tools.jib") version "3.4.0"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
}

group = "com"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_20
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

jib {
    from {
        image = "openjdk:20-jdk"
    }
    to {
        image = "milkcoke/spring-yaml"
        tags = setOf(project.version.toString())
    }
    container {
        jvmFlags = listOf("-Dspring.profiles.active=dev")
    }
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "20"
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}
