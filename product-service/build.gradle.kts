dependencies {
  //  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

}

configurations.forEach {
  it.exclude(module = "spring-boot-starter-tomcat")
  it.exclude(module = "mockito-core")
}
