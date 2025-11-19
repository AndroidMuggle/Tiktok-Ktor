rootProject.name = "tiktok-ktor"

dependencyResolutionManagement {
    repositories {
        maven ("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/public") // 阿里云镜像
        maven("https://maven.aliyun.com/repository/central") // Central 仓库镜像
        maven("https://maven.aliyun.com/repository/gradle-plugin") // Gradle 插件镜像
        maven("https://maven.aliyun.com/repository/apache-snapshots") // apache 镜像

        mavenCentral()
    }
}
