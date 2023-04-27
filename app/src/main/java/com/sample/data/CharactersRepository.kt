package com.sample.data

import androidx.lifecycle.LiveData
import com.sample.data.model.CharactersData

interface CharactersRepository {

    suspend fun fetchCharacters(): Result<Unit>

    fun getCharacters(): LiveData<CharactersData>
}
