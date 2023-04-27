package com.sample.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.data.CharactersRepository
import com.sample.data.model.CharactersData
import com.sample.data.network.CharacterApi
import com.sample.data.util.toCharactersData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

class CharactersRepositoryImpl(
    private val characterApi: CharacterApi,
    private val ioDispatcher: CoroutineDispatcher
) : CharactersRepository {

    // In-memory cache
    private val characters = MutableLiveData<CharactersData>()

    override suspend fun fetchCharacters(): Result<Unit> {
        return withContext(ioDispatcher) {
            val response = characterApi.getCharacters()
            if (response.isSuccessful) {
                characters.postValue(response.body()!!.toCharactersData())
                Result.success(Unit)
            } else Result.failure(IOException(response.message()))
        }
    }

    override fun getCharacters(): LiveData<CharactersData> = characters
}