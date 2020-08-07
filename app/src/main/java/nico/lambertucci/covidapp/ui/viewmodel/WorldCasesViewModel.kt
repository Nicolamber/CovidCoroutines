package nico.lambertucci.covidapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.covidapp.domain.data.main.WorldCases
import nico.lambertucci.covidapp.domain.repository.CovidRepository

class WorldCasesViewModel(private val repository: CovidRepository) : ViewModel() {


    suspend fun getAllCases(): LiveData<WorldCases>? {
       return repository.getAllCases()
    }
}