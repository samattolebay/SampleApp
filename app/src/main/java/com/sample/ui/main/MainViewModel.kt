package com.sample.ui.main

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.sample.MyApplication
import com.sample.data.CharactersRepository
import com.sample.data.model.CharactersData
import kotlinx.coroutines.launch

class MainViewModel(
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _characters: MutableLiveData<CharactersData> =
        charactersRepository.getCharacters() as MutableLiveData
    val characters: LiveData<CharactersData> = _characters

    init {
        viewModelScope.launch {
            val response = charactersRepository.fetchCharacters()
            response.onFailure {
                _error.postValue(it.message.toString())
            }
        }
    }

    // Define ViewModel factory in a companion object
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return MainViewModel(
                    (application as MyApplication).charactersRepository,
                    savedStateHandle
                ) as T
            }
        }
    }
}
