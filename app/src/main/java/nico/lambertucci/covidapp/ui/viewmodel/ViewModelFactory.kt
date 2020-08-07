package nico.lambertucci.covidapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nico.lambertucci.covidapp.domain.repository.CovidRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: CovidRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            WorldCasesViewModel::class.java -> {
                return WorldCasesViewModel(this.repository) as T
            }
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}