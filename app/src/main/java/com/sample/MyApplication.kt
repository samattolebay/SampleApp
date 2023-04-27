package com.sample

import android.app.Application
import com.sample.data.network.CharacterApi
import com.sample.data.repo.CharactersRepositoryImpl
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.duckduckgo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val characterApi by lazy {
        retrofit.create(CharacterApi::class.java)
    }

    private val ioDispatcher by lazy {
        Dispatchers.IO
    }

    val charactersRepository by lazy {
        CharactersRepositoryImpl(characterApi, ioDispatcher)
    }
}
