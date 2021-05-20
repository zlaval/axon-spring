package com.zlrx.springaxon.product.query

import com.zlrx.springaxon.product.controller.model.ProductResponse
import com.zlrx.springaxon.product.repository.ProductRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.flux
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class ProductsQueryHandler constructor(
    private val productRepository: ProductRepository
) {

    @QueryHandler
    fun findProduct(query: FindProductQuery) = flux<ProductResponse> {
        productRepository.findAll().map {
            ProductResponse(
                productId = it.productId,
                quantity = it.quantity,
                title = it.title,
                price = it.price
            )
        }
    }

}