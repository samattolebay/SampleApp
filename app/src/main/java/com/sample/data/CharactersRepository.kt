package com.sample.data

import androidx.lifecycle.LiveData
import com.sample.data.model.CharacterData

interface CharactersRepository {

    suspend fun fetchCharacters(query: String): Result<Unit>

    fun getCharacters(): LiveData<List<CharacterData>>

    suspend fun getCharacter(name: String): Result<CharacterData>

    suspend fun getFilteredCharacters(query: String?): Result<List<CharacterData>>
}
