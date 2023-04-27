package com.sample.data

import androidx.lifecycle.LiveData
import com.sample.data.model.CharacterData
import com.sample.data.model.CharactersData

interface CharactersRepository {

    suspend fun fetchCharacters(query: String): Result<Unit>

    fun getCharacters(): LiveData<CharactersData>

    suspend fun getCharacter(name: String): Result<CharacterData>
}
