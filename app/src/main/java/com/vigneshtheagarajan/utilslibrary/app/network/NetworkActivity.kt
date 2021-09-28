package com.vigneshtheagarajan.utilslibrary.app.network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vigneshtheagarajan.utilslibrary.R
import com.vigneshtheagarajan.utilslibrary.app.network.NetRequest.commonService
import kotlinx.android.synthetic.main.activity_network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        button.setOnClickListener {
            callServer()
        }
    }

    private fun callServer() {
        GlobalScope.launch {
           val callServer =  commonService.getGithubRepos()
            withContext(Dispatchers.Main) {
                textView.text = "$callServer"
            }
        }
    }
}