package com.sample.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.data.CharactersRepository
import com.sample.data.model.CharacterData
import com.sample.data.network.CharacterApi
import com.sample.data.util.toCharacterData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

class CharactersRepositoryImpl(
    private val characterApi: CharacterApi,
    private val ioDispatcher: CoroutineDispatcher,
) : CharactersRepository {

    // In-memory cache
    private val characters = MutableLiveData<List<CharacterData>>()

    override suspend fun fetchCharacters(query: String): Result<Unit> {
        return withContext(ioDispatcher) {
            val response = characterApi.getCharacters(query)
            if (response.isSuccessful) {
                characters.postValue(response.body()!!.relatedTopics.map { it.toCharacterData() })
                Result.success(Unit)
            } else Result.failure(IOException(response.message()))
        }
    }

    override fun getCharacters(): LiveData<List<CharacterData>> = characters

    override suspend fun getCharacter(name: String): Result<CharacterData> {
        return withContext(ioDispatcher) {
            val character = characters.value?.find { it.name == name }
            if (character != null) Result.success(character) else Result.failure(IOException("No character found!"))
        }
    }

    override suspend fun getFilteredCharacters(query: String?): Result<List<CharacterData>> {
        return withContext(ioDispatcher) {
            if (query == null) Result.success(characters.value ?: emptyList())
            else {
                val curData = characters.value ?: emptyList()
                val result = curData.filter { it.text.contains(query, true) }
                Result.success(result)
            }
        }
    }
}
