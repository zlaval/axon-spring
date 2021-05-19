package com.zlrx.springaxon.product.event

import com.zlrx.springaxon.product.domain.Product
import com.zlrx.springaxon.product.repository.ProductRepository
import kotlinx.coroutines.reactor.mono
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
class ProductEventHandler(
    private val productRepository: ProductRepository
) {

    @EventHandler
    fun on(event: ProductCreatedEvent) {
        val product = Product(
            productId = event.productId,
            quantity = event.quantity,
            price = event.price,
            title = event.title
        )
        mono { productRepository.save(product) }.subscribe()
    }

}