import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.30"
    id("java")
    id("idea")
    id("application")
    id("org.jmailen.kotlinter") version "3.4.0"
    id("com.adarshr.test-logger") version "3.0.0"
    id("com.google.cloud.tools.jib") version "2.8.0"
    id("org.springframework.boot") version "2.5.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.experimental.aot") version "0.10.0-SNAPSHOT"//  apply false
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

allprojects {

    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "application")
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "com.google.cloud.tools.jib")
    apply(plugin = "com.adarshr.test-logger")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    group = "com.zlrx.examples"
    version = "1.0.0"
    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }

}

subprojects {

    kotlinter {
        ignoreFailures = false
        disabledRules = arrayOf("import-ordering", "filename")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

//    configurations.forEach {
//        it.exclude(module = "spring-boot-starter-tomcat")
//        it.exclude(module = "mockito-core")
//    }

    extra["springCloudVersion"] = "2020.0.2"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    }

    //tasks.compileKotlin { dependsOn(tasks.lintKotlin) }
    //tasks.lintKotlin { dependsOn(tasks.formatKotlin) }

}





