package com.paulolima.namegenerator.architecture

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.paulolima.namegenerator.service.DispatcherServiceInterface
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Injects a store into a [Composable] View
 */
@Composable
inline fun <reified S: ViewModel> store(): S {
    return hiltViewModel()
}

/**
 * Provides a Stub Store. Utility extension for Previews.
 */
fun <Action, State> stubStore(state: State, dispatcher: DispatcherServiceInterface = stubDispatcher()): Store<Action, State> {
    return Store(state, dispatcher)
}

private fun stubDispatcher(): DispatcherServiceInterface {
    return object: DispatcherServiceInterface {
        override val Default: CoroutineDispatcher
            get() = throw NotImplementedError()
        override val IO: CoroutineDispatcher
            get() = throw NotImplementedError()
        override val Main: CoroutineDispatcher
            get() = throw NotImplementedError()
    }
}