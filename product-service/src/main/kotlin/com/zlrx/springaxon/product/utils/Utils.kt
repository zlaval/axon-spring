package com.zlrx.springaxon.product.utils

import kotlinx.coroutines.reactive.awaitSingle
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.UUID


suspend fun asyncUUIDGenerator() = nonBlockingUUIDGenerator().awaitSingle()

fun nonBlockingUUIDGenerator(): Mono<String> = Mono
    .fromCallable { UUID.randomUUID().toString() }
    .subscribeOn(Schedulers.boundedElastic())

