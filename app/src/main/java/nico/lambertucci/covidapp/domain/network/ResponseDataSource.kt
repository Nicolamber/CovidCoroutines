package nico.lambertucci.covidapp.domain.network

import androidx.lifecycle.LiveData
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse
import nico.lambertucci.covidapp.domain.data.main.WorldCases

interface ResponseDataSource {
    val allCasesResponse: LiveData<WorldCases>
    val countryCases: LiveData<CountryResponse>

    suspend fun fetchWorldCases()
    suspend fun fetchCountryCases()
}