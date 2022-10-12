package com.study.til.domain.usecase

fun interface GetStringItemsUseCase : suspend () -> List<String>
fun interface GetIntItemsUseCase : suspend () -> List<Int>
fun interface GetDoubleItemsUseCase : suspend () -> List<Double>
