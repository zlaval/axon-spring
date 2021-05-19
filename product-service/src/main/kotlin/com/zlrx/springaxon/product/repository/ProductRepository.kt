package com.zlrx.springaxon.product.repository

import com.zlrx.springaxon.product.domain.Product
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProductRepository : CoroutineCrudRepository<Product, String> {

    fun findByProductId(productId: String): Product

}