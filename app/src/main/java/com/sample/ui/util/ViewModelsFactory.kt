package com.sample.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.sample.MyApplication
import com.sample.ui.details.DetailsViewModel
import com.sample.ui.main.ListViewModel

val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        // Get the Application object from extras
        val application =
            checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as MyApplication
        // Create a SavedStateHandle for this ViewModel from extras
        val savedStateHandle = extras.createSavedStateHandle()
        val charactersRepository = application.charactersRepository

        return when {
            modelClass.isAssignableFrom(ListViewModel::class.java) ->
                ListViewModel(charactersRepository, savedStateHandle) as T
            modelClass.isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(charactersRepository, savedStateHandle) as T
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
