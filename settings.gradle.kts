pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo.spring.io/milestone") }
        gradlePluginPortal()
    }
}
rootProject.name = "axon-spring"
include("product-service")
include("eureka-discovery")
include("api-gateway")
