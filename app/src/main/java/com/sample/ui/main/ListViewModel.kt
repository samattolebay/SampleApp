package com.sample.ui.main

import androidx.lifecycle.*
import com.sample.data.CharactersRepository
import com.sample.ui.util.toCharactersViewData
import kotlinx.coroutines.launch

class ListViewModel(
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _characters: MutableLiveData<CharactersViewData> =
        charactersRepository.getCharacters().map { it.toCharactersViewData() } as MutableLiveData
    val characters: LiveData<CharactersViewData> = _characters

    fun fetchCharacters(query: String) {
        viewModelScope.launch {
            val response = charactersRepository.fetchCharacters(query)
            response.onFailure {
                _error.postValue(it.message.toString())
            }
        }
    }
}
