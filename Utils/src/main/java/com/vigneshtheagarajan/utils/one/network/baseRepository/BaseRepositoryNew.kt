package com.vigneshtheagarajan.utils.one.network.baseRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vigneshtheagarajan.utils.one.log
import com.vigneshtheagarajan.utils.one.view.hideNewLoader
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepositoryNew<T>(@PublishedApi internal val service: T) {

//abstract fun loadData(data: Any? = null): LiveData<T>?


    inline fun <C>fetchData(crossinline call: (T) -> Deferred<Response<C>>): LiveData<C> {
        val result = MutableLiveData<C>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        log("response: ${response.raw()}")
                        result.value = response.body()
                    } else {
                        log("Error occurred with code ${response.raw()}")
                        log("Error occurred with code ${response.message()}")
                        log("Error occurred with code ${response.errorBody()}")
//                    if (errorToast)
//                    toast("Error occurred with code ${response.message()}")
//                        result.value =  null
                    }
                    hideNewLoader()
                } catch (e: HttpException) {
                    hideNewLoader()
                    log("Error HttpException: ${e.message()}")
                    log("Error HttpException: ${e.message}")
                } catch (e: Throwable) {
                    hideNewLoader()
                    log("Error Throwable: ${e.message}")
                    log("Error Throwable: ${e.stackTrace}")
                } catch (e: Exception) {
                    hideNewLoader()
                    log("Error Exception: ${e.message}")
                    log("Error Exception: ${e.stackTrace}")
                }
            }
        }

        return result
    }
}
