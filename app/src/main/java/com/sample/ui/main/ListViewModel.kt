package com.sample.ui.main

import androidx.lifecycle.*
import com.sample.data.CharactersRepository
import com.sample.ui.util.toCharacterViewDataList
import kotlinx.coroutines.launch

class ListViewModel(
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _characters: MutableLiveData<List<CharacterViewData>> =
        charactersRepository.getCharacters()
            .map { it.toCharacterViewDataList() } as MutableLiveData
    val characters: LiveData<List<CharacterViewData>> = _characters

    fun fetchCharacters(query: String) {
        viewModelScope.launch {
            val response = charactersRepository.fetchCharacters(query)
            response.onFailure {
                _error.postValue(it.message.toString())
            }
        }
    }

    fun search(query: String?) {
        viewModelScope.launch {
            charactersRepository.getFilteredCharacters(query)
                .onSuccess {
                    _characters.postValue(it.toCharacterViewDataList())
                }
                .onFailure {
                    _error.postValue(it.message.toString())
                }
        }
    }

    fun reset() {
        search("")
    }
}
