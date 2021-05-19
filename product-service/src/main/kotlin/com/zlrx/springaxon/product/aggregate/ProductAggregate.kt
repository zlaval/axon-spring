package com.zlrx.springaxon.product.aggregate

import com.zlrx.springaxon.product.command.CreateProductCommand
import com.zlrx.springaxon.product.event.ProductCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

@Aggregate
class ProductAggregate @CommandHandler constructor(
    private val createProductCommand: CreateProductCommand
) {

    @AggregateIdentifier
    private lateinit var productId: String
    private lateinit var title: String
    private lateinit var price: BigDecimal
    private var quantity: Int = 0

    init {
        validate()
        apply()
    }

    @EventSourcingHandler
    fun on(productCreatedEvent: ProductCreatedEvent) {
        productId = productCreatedEvent.productId
        title = productCreatedEvent.title
        price = productCreatedEvent.price
        quantity = productCreatedEvent.quantity
    }

    private fun validate() {
        if (createProductCommand.price <= BigDecimal.ZERO) {
            throw IllegalArgumentException("Price cannot be less then one.")
        }
    }

    private fun apply() {
        val productCreatedEvent = ProductCreatedEvent(
            productId = createProductCommand.productId,
            title = createProductCommand.title,
            price = createProductCommand.price,
            quantity = createProductCommand.quantity
        )
        AggregateLifecycle.apply(productCreatedEvent)
    }

}