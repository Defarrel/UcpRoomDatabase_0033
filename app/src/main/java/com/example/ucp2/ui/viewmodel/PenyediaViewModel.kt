package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.HealthApp

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                HealthApp().containerApp.repositoryDokter
            )
        }
    }
}
fun CreationExtras.HealthApp(): HealthApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HealthApp)