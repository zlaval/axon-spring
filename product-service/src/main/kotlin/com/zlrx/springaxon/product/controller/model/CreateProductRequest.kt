package com.zlrx.springaxon.product.controller.model

import java.math.BigDecimal

data class CreateProductRequest(
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)
