package com.paulolima.namegenerator.service

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherService: DispatcherServiceInterface {

    override val Default: CoroutineDispatcher = Dispatchers.Default
    override val IO = Dispatchers.IO
    override val Main = Dispatchers.Main
}