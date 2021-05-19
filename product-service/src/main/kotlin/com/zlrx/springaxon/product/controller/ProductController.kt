package com.zlrx.springaxon.product.controller

import com.zlrx.springaxon.product.command.CreateProductCommand
import com.zlrx.springaxon.product.controller.model.CreateProductRequest
import com.zlrx.springaxon.product.utils.asyncUUIDGenerator
import kotlinx.coroutines.reactive.awaitSingle
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ProductController {
    @Bean
    fun routerFunction(
        environment: Environment,
        commandGateway: ReactorCommandGateway
    ) = coRouter {

        "api/v1/product".nest {

            POST("") {
                val createProductRequest = it.awaitBody<CreateProductRequest>()
                val createProductCommand = CreateProductCommand(
                    price = createProductRequest.price,
                    quantity = createProductRequest.quantity,
                    title = createProductRequest.title,
                    productId = asyncUUIDGenerator()
                )
                val result = commandGateway.send<String>(createProductCommand).awaitSingle()
                ServerResponse.ok().bodyValueAndAwait(result)
            }

            GET("") {
                ServerResponse.ok().bodyValueAndAwait("Hello world from ${environment.getProperty("local.server.port")}")
            }


        }
    }
}