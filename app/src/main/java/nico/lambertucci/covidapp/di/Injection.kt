package nico.lambertucci.covidapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nico.lambertucci.covidapp.domain.network.ResponseDataSourceImpl
import nico.lambertucci.covidapp.domain.repository.CovidRepositoryImpl
import nico.lambertucci.covidapp.ui.viewmodel.ViewModelFactory

object Injection {
    private val covidDataSource = ResponseDataSourceImpl()
    private val covidRepository = CovidRepositoryImpl()
    private val viewModelFactory = ViewModelFactory(covidRepository)

    fun getDataSource() = covidDataSource

    fun getViewModelFactory(): ViewModelProvider.Factory{
        return  viewModelFactory
    }
}