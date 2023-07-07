package com.example.growighassignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.growighassignment.repository.ItemRepository

class ItemViewModelProviderFactory(val repository: ItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(repository) as T
    }
}