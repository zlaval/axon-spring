package com.zlrx.springaxon.product.query

import com.zlrx.springaxon.product.controller.model.ProductResponse
import com.zlrx.springaxon.product.repository.ProductRepository
import com.zlrx.springaxon.product.utils.toList
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class ProductsQueryHandler constructor(
    private val productRepository: ProductRepository
) {

    @QueryHandler
    fun findProduct(query: FindProductQuery) =
        runBlocking {//TODO fixit
            productRepository.findAll().map {
                ProductResponse(
                    productId = it.productId,
                    quantity = it.quantity,
                    title = it.title,
                    price = it.price
                )
            }.toList()
        }


//    = flux<ProductResponse> {
//        productRepository.findAll().map {
//            ProductResponse(
//                productId = it.productId,
//                quantity = it.quantity,
//                title = it.title,
//                price = it.price
//            )
//        }
//    }

}