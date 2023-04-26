package com.sample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.sample.data.network.CharacterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    val msg = MutableLiveData<String>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.duckduckgo.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val characterApi = retrofit.create(CharacterApi::class.java)

            val x = characterApi.getCharacters()

            msg.postValue(x.body().toString())
        }
    }
}
