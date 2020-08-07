package nico.lambertucci.covidapp.domain.network

import kotlinx.coroutines.Deferred
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse
import nico.lambertucci.covidapp.domain.data.main.WorldCases
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getAllCasesAsync():Deferred<WorldCases>

    @GET("countries")
    fun getCasesByCountryAsync():Deferred<CountryResponse>
}