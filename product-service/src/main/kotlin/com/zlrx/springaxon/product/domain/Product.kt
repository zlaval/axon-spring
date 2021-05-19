package com.zlrx.springaxon.product.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
data class Product(
    @Id
    val _id: String? = null,
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)