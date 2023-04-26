package com.sample

import android.app.Application
import com.sample.data.network.CharacterApi
import retrofit2.Retrofit

class MyApplication : Application() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.duckduckgo.com/")
            .build()
    }

    val characterApi by lazy {
        retrofit.create(CharacterApi::class.java)
    }
}
