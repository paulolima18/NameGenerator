package com.paulolima.namegenerator.service

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherServiceInterface {

    val Default: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
}