dependencies {
    //  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.axonframework:axon-spring-boot-starter:4.5")
    implementation("org.axonframework.extensions.reactor:axon-reactor-spring-boot-starter:4.5")
    // implementation("com.google.guava:guava:30.1.1-jre")
}

configurations.forEach {
    it.exclude(module = "spring-boot-starter-tomcat")
    it.exclude(module = "mockito-core")
}
