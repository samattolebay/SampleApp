package com.sample.data.network

import com.sample.data.network.model.CharactersResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET(".")
    suspend fun getCharacters(
        @Query("q") q: String,
        @Query("format") format: String = "json",
    ): Response<CharactersResponseData>
}
