val kotlin_version: String by project
val logback_version: String by project
val mongodb_version: String by project

plugins {
    kotlin("jvm") version "2.2.20"
    id("io.ktor.plugin") version "3.3.2"
}

group = "com.muggle"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-cors:3.3.2")
    implementation("io.ktor:ktor-server-csrf:3.3.2")
    implementation("io.ktor:ktor-server-hsts:3.3.2")
    implementation("io.ktor:ktor-server-auth:3.3.2")
    implementation("io.ktor:ktor-server-auth-jwt:3.3.2")
    implementation("io.ktor:ktor-server-auth:3.3.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-content-negotiation:3.3.2")
    implementation("org.openfolder:kotlin-asyncapi-ktor:3.1.3")
    implementation("io.ktor:ktor-server-compression:3.3.2")
    implementation("io.ktor:ktor-server-websockets:3.3.2")
    implementation("io.ktor:ktor-server-sessions:3.3.2")
    implementation("io.ktor:ktor-server-partial-content:3.3.2")
    implementation("io.ktor:ktor-server-caching-headers:3.3.2")
    implementation("io.ktor:ktor-server-default-headers-jvm:3.3.2")
    implementation("io.ktor:ktor-server-conditional-headers:3.3.2")
    implementation("io.ktor:ktor-server-call-logging:3.3.2")
    implementation("io.ktor:ktor-server-call-id:3.3.2")
    implementation("io.ktor:ktor-server-call-logging:3.3.2")
    implementation("org.litote.kmongo:kmongo:${mongodb_version}")
    implementation("org.ehcache:ehcache:3.11.1")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-serialization-jackson:3.3.2")
    implementation("io.ktor:ktor-serialization-gson:3.3.2")
    implementation("com.aliyun:dypnsapi20170525:2.0.0")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

kotlin {
    jvmToolchain(24)
}