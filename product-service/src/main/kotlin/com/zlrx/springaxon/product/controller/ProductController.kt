package com.zlrx.springaxon.product.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ProductController {
    @Bean
    fun routerFunction(environment: Environment) = coRouter {
        "api/v1/product".nest {

            GET("") {
                ServerResponse.ok().bodyValueAndAwait("Hello world from ${environment.getProperty("local.server.port")}")
            }


        }
    }
}