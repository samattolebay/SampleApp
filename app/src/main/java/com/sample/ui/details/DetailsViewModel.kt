package com.sample.ui.details

import androidx.lifecycle.*
import com.sample.data.CharactersRepository
import com.sample.data.model.CharacterData
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _character = MutableLiveData<CharacterData>()
    val character: LiveData<CharacterData> = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCharacter(name: String) {
        viewModelScope.launch {
            charactersRepository.getCharacter(name)
                .onSuccess {_character.postValue(it)  }
                .onFailure { _error.postValue(it.message) }
        }
    }
}
