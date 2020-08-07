package nico.lambertucci.covidapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse
import nico.lambertucci.covidapp.domain.repository.CovidRepository

class CountryCasesViewModel( private val repository: CovidRepository) : ViewModel() {

    suspend fun getCovidByCountry(): LiveData<CountryResponse>{
        return repository.getCasesByCountry()
    }
}