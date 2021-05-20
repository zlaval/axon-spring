package com.zlrx.springaxon.product.controller.model

import java.math.BigDecimal

data class ProductResponse(
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)
