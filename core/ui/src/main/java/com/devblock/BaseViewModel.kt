package com.devblock

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel: ViewModel() {

    private val viewModelJob = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val handlerException = CoroutineExceptionHandler { _, exception ->
        manageException(exception)
    }

    open fun manageException(exception: Any){

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}