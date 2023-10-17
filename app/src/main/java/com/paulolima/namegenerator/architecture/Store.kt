package com.paulolima.namegenerator.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulolima.namegenerator.service.DispatcherServiceInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A [Store] is an object that can be used to observe state changes and send actions.
 * It is intended to be more commonly used in views.
 * In Compose applications, a [Store] is accessed most commonly using the [StoreExtensions#store()] extension, that provides a [Store] that is lifecycle aware.
 *
 * **Thread safety**
 *
 * The [Store] class is not thread-safe.
 * Background processing of actions is promoted over the [send] function, which delegates [processAction] to a background thread.
 * Synchronous [state] updates are promoted over the [updateState] function, which posts [state] updates from the main thread.
 */
open class Store<in Action, State>(
    initialState: State,
    private val dispatcherService: DispatcherServiceInterface
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state

    open suspend fun processAction(action: Action) {
        throw NotImplementedError("processAction must be implemented")
    }

    fun send(action: Action) {
        viewModelScope.launch(dispatcherService.Main) {
            processAction(action)
        }
    }

    internal fun updateState(function: (State) -> State) {
        viewModelScope.launch(dispatcherService.Main) {
            _state.update { function(state.value) }
        }
    }
}