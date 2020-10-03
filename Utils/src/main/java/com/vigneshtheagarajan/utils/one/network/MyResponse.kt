package com.vigneshtheagarajan.utils.one.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException


data class MyResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)

suspend fun <T : Any> MyResponse<T>.executeResponse(
    successBlock: (suspend CoroutineScope.() -> Unit)? = null,
    errorBlock: (suspend CoroutineScope.() -> Unit)? = null
): Result<T> {
    return coroutineScope {
        if (errorCode == -1) {
            errorBlock?.let { it() }
            Result.Error(IOException(errorMsg))
        } else {
            successBlock?.let { it() }
            Result.Success(data)
        }
    }
}

suspend fun <T : Any> MyResponse<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): MyResponse<T> {
    return coroutineScope {
        if (errorCode != -1) successBlock?.invoke(this, this@doSuccess.data)
        this@doSuccess
    }

}

suspend fun <T : Any> MyResponse<T>.doError(errorBlock: (suspend CoroutineScope.(String) -> Unit)? = null): MyResponse<T> {
    return coroutineScope {
        if (errorCode == -1) errorBlock?.invoke(this, this@doError.errorMsg)
        this@doError
    }
}