package com.vigneshtheagarajan.utilslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vigneshtheagarajan.utils.one.network.BaseRepositoryNew5
import com.vigneshtheagarajan.utils.one.network.NetServiceCreator
import com.vigneshtheagarajan.utils.one.toast
import com.vigneshtheagarajan.utils.one.view.setDatePickerET
import com.vigneshtheagarajan.utils.one.view.setTimepicker
import com.vigneshtheagarajan.utilslibrary.NetRequest.commonService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        picker()
    }

    private fun picker() {
        time.setTimepicker()
        date.setDatePickerET()
        rv_data.setOnClickListener {
            com.vigneshtheagarajan.utils.one.toast("test")
            AddProductsRepo(commonService).deleteData(null).observe(this){
                     toast("${it.videos[0].name}")
            }

        }

    }


}
interface ApiCommonService {
    @GET("master/test.json")
    suspend fun gettest(): Response<String>
    @GET("test.json")
    fun gettest1(): Deferred<Response<tt>>
}

data class ttt(val aa:String)



//data class ttt1(val aa:String)

data class tt(
    val videos: List<Video>
) { data class Video(
        val id: String,
        val name: String
    )
}

class AddProductsRepo(service: ApiCommonService) : BaseRepositoryNew5<ApiCommonService>(service) {

     fun deleteData(data: Any?) = fetchData {
         commonService.gettest1()
    }


}


object NetRequest {
    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator.apply {
            baseUrl = "https://raw.githubusercontent.com/vicky-droid/netWork/master/"
            errorToast =true
            debug =true
            chucker =true
        }
        NetServiceCreator.create(ApiCommonService::class.java)
    }



}


