package nico.lambertucci.covidapp.domain.repository

import androidx.lifecycle.LiveData
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse
import nico.lambertucci.covidapp.domain.data.main.WorldCases

interface CovidRepository {

    suspend fun getAllCases(): LiveData<WorldCases>

    suspend fun getCasesByCountry(): LiveData<CountryResponse>
}