package com.zlrx.springaxon.product.controller

import com.zlrx.springaxon.product.command.CreateProductCommand
import com.zlrx.springaxon.product.controller.model.CreateProductRequest
import com.zlrx.springaxon.product.controller.model.ProductResponse
import com.zlrx.springaxon.product.query.FindProductQuery
import com.zlrx.springaxon.product.utils.asyncUUIDGenerator
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter
import reactor.core.publisher.Flux

@Configuration
class ProductController {
    @Bean
    fun routerFunction(
        environment: Environment,
        commandGateway: ReactorCommandGateway,
        queryGateway: ReactorQueryGateway
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
                val productQuery = FindProductQuery()
                val result =
                    queryGateway.query(productQuery, ResponseTypes.multipleInstancesOf(ProductResponse::class.java))
                        .flatMapMany { Flux.fromIterable(it) }
                ServerResponse.ok().bodyAndAwait(result.asFlow())
            }

        }
    }
}