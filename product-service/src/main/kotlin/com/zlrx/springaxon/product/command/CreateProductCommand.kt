package com.zlrx.springaxon.product.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal

data class CreateProductCommand(
    @TargetAggregateIdentifier
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)