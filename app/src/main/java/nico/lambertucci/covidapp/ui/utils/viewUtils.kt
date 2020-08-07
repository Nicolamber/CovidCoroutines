package nico.lambertucci.covidapp.ui.utils

import androidx.appcompat.widget.Toolbar

interface viewUtils {
    fun showLoadingBar()
    fun hideLoadingBar()
    fun setupToolbar(toolbar:Toolbar)
}