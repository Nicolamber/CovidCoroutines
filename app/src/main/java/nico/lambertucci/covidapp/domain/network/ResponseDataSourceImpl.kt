package nico.lambertucci.covidapp.domain.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse
import nico.lambertucci.covidapp.domain.data.main.WorldCases
import java.io.IOException

class ResponseDataSourceImpl : ResponseDataSource {

    private val TAG = "DataSource"
    private val apiConnection = RetrofitBuilder.initialize()

    private val worldCasesResponse = MutableLiveData<WorldCases>()
    private val countriesCasesResponse = MutableLiveData<CountryResponse>()

    override val countryCases: LiveData<CountryResponse>
        get() = countriesCasesResponse

    override val allCasesResponse: LiveData<WorldCases>
        get() = worldCasesResponse

    override suspend fun fetchWorldCases() {
        try {
            val worldCases = apiConnection?.getAllCasesAsync()?.await()
            worldCasesResponse.postValue(worldCases)
        }catch (e: IOException){
            Log.e(TAG,"Error al intentar retornar los casos globales")
        }
    }

    override suspend fun fetchCountryCases() {
        try {
            val countries = apiConnection?.getCasesByCountryAsync()?.await()
            countriesCasesResponse.postValue(countries)
        }catch (e: IOException){
            Log.e(TAG, "Error al intentar retornar casos por paises")
        }
    }
}