package nico.lambertucci.covidapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nico.lambertucci.covidapp.domain.data.main.WorldCases
import nico.lambertucci.covidapp.di.Injection
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse

class CovidRepositoryImpl : CovidRepository {

    private val worldCasesResponse = MutableLiveData<WorldCases>()
    private val countryCasesResponse = MutableLiveData<CountryResponse>()

    private val dataSource = Injection.getDataSource()

    init {
        dataSource.allCasesResponse.observeForever{
            worldCasesResponse.postValue(it)
        }
        dataSource.countryCases.observeForever{
            countryCasesResponse.postValue(it)
        }
    }

    override suspend fun getAllCases(): LiveData<WorldCases> {
        return withContext(Dispatchers.IO){
            getWorldCasesData()
            return@withContext worldCasesResponse
        }
    }

    override suspend fun getCasesByCountry(): LiveData<CountryResponse> {
        return withContext(Dispatchers.IO){
            getCountryCases()
            return@withContext countryCasesResponse
        }
    }

    private suspend fun getCountryCases(){
        dataSource.fetchCountryCases()
    }

    private suspend fun getWorldCasesData(){
        dataSource.fetchWorldCases()
    }
}