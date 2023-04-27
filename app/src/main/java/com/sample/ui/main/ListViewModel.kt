package com.sample.ui.main

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.sample.MyApplication
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

                return ListViewModel(
                    (application as MyApplication).charactersRepository,
                    savedStateHandle
                ) as T
            }
        }
    }
}