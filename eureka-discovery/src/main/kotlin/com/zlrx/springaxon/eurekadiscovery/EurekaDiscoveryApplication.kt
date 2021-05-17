package com.zlrx.springaxon.eurekadiscovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class EurekaDiscoveryApplication

fun main(args: Array<String>) {
    runApplication<EurekaDiscoveryApplication>(*args)
}
