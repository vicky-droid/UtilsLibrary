package com.vigneshtheagarajan.utilslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.vigneshtheagarajan.utils.one.network.baseRepository.BaseRepositoryNew
import com.vigneshtheagarajan.utils.one.network.baseRepository.NetServiceCreator
import com.vigneshtheagarajan.utils.one.toast
import com.vigneshtheagarajan.utils.one.view.setDatePickerET
import com.vigneshtheagarajan.utils.one.view.setTimepicker
import com.vigneshtheagarajan.utilslibrary.NetRequest.commonService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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
//            com.vigneshtheagarajan.utils.one.toast("test")
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

    @POST("login")
    fun user(@Body data: JsonObject): Deferred<Response<tt>>
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

class AddProductsRepo(service: ApiCommonService) : BaseRepositoryNew<ApiCommonService>(service) {

     fun deleteData(data: Any?) = fetchData {
//         commonService.gettest1()
         val data= JsonObject()
         data.addProperty("email","viki@letz.com")
         data.addProperty("password","123456")
         data.addProperty("device_id","deviceId")
         data.addProperty("device_token","device_token")
         data.addProperty("device_type","1")
         data.addProperty("_v","4")

         commonService.user(data)
    }


}


object NetRequest {
    val authToken="eyJhbGciOiJSUzI1NiJ9.JDYmTHxQQ3oxakoreW09S2peYkYnIyxsUXlHaCN2K0lMRHc0LjpLWUNBcURkNF5POVp1aUQ3JHAlMj0raCR3.HaxTEMeEmA9KvfPUY1yad_jK95e64bluAvHo5lQPBWJCmVOxN7d-K4BSoFvdkfXHuiMeljXurvmB2yvEFHw4GSxR516mLoOmYrGZw4rHbg8PtxmI64bbKm3ugTvtNVRFQcqShOXJZF9nGqLlTzpeTXORCwLNHs3SA1jD_LunKV6Ha-zeMEN0WpxNJZTgp5WzdhWPTq1it9-DCPxI4kqdVNq88WPkb9bvnegw_eIMo15ZJSGuSWbuFJK4TPyu4ConzL3Vc9mgPjHm_NdHqTKOJ7ecnTMPlpzNlC8yh-GIVjlrMAEtBdESRI7e0lZamax3d2vgu9XrRAnlpvo-7r82nA"

    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {

//        NetServiceCreator().apply {
////            baseUrl = "https://raw.githubusercontent.com/vicky-droid/netWork/master/"
//            errorToast =true
//            debug =true
//            chucker =true
////            authToken="eyJhbGciOiJSUzI1NiJ9.JDYmTHxQQ3oxakoreW09S2peYkYnIyxsUXlHaCN2K0lMRHc0LjpLWUNBcURkNF5POVp1aUQ3JHAlMj0raCR3.HaxTEMeEmA9KvfPUY1yad_jK95e64bluAvHo5lQPBWJCmVOxN7d-K4BSoFvdkfXHuiMeljXurvmB2yvEFHw4GSxR516mLoOmYrGZw4rHbg8PtxmI64bbKm3ugTvtNVRFQcqShOXJZF9nGqLlTzpeTXORCwLNHs3SA1jD_LunKV6Ha-zeMEN0WpxNJZTgp5WzdhWPTq1it9-DCPxI4kqdVNq88WPkb9bvnegw_eIMo15ZJSGuSWbuFJK4TPyu4ConzL3Vc9mgPjHm_NdHqTKOJ7ecnTMPlpzNlC8yh-GIVjlrMAEtBdESRI7e0lZamax3d2vgu9XrRAnlpvo-7r82nA"
//        }

        NetServiceCreator()
            .setBaseUrl("https://raw.githubusercontent.com/vicky-droid/netWork/master/")
            .setAuthToken(authToken)
            .enableDebug(true)
            .enableChucker(true)
            .create(ApiCommonService::class.java)

//        NetServiceCreator.create(ApiCommonService::class.java)
    }



}


