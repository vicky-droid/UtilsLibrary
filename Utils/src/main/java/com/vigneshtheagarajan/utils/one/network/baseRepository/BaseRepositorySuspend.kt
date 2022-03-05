package com.vigneshtheagarajan.utils.one.network.baseRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.vigneshtheagarajan.utils.one.log
import com.vigneshtheagarajan.utils.one.view.hideNewLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


//abstract class BaseRepositorySuspend<T>(@PublishedApi internal val service: T) {
abstract class BaseRepositorySuspend<T>(val service: T) {

    inline fun <C> run(crossinline bar: suspend () -> C): LiveData<C> {
        log("run")

        return liveData(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                try {
                    log("run1")
                    val a = bar()
                    emit(a)
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

    }


}

