package com.devblock.base



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devblock.utils.AppException

import com.devblock.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


abstract class BaseViewModel: ViewModel() {

    var progressLiveEvent = SingleLiveEvent<Boolean>()
    var errorMessage = SingleLiveEvent<Any>()
    protected val _state = MutableLiveData<UIState>()
    val state: LiveData<UIState>
        get() = _state


    val handlerException = CoroutineExceptionHandler { _, exception ->
        manageException(exception)
    }

    open fun manageException(exception: Any){
        viewModelScope.launch {
            if (exception is AppException) {

                errorMessage.value = exception

            } else if (exception is Throwable){
                errorMessage.value = exception.localizedMessage
            }
            progressLiveEvent.value = false
        }

    }

    inline fun  launchAsync(
        crossinline execute: suspend () -> Unit,
        crossinline onSuccess:() -> Unit,
        showProgress: Boolean = true
    ) {
        viewModelScope.launch {
            if (showProgress)
                progressLiveEvent.value = true
            try {
                val result = execute()

            } catch (ex: Exception) {
                errorMessage.value = ex.message
            } finally {
                progressLiveEvent.value = false
            }
        }
    }

    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (Flow<T>) -> Unit
    ) {
        viewModelScope.launch(handlerException) {
            try {

                val result = execute()
                onSuccess(result)

            } catch (ex: Exception) {
                errorMessage.value = ex.message
            }
        }
    }
}