dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    //  tasks.compileKotlin { dependsOn(tasks.lintKotlin) }
    //tasks.lintKotlin { dependsOn(tasks.formatKotlin) }
}
