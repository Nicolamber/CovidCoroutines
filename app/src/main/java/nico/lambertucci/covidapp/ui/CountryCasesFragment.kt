package nico.lambertucci.covidapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nico.lambertucci.covidapp.R
import nico.lambertucci.covidapp.ui.viewmodel.CountryCasesViewModel

class CountryCasesFragment : Fragment() {

    companion object {
        fun newInstance() = CountryCasesFragment()
    }

    private lateinit var viewModel: CountryCasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_cases_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryCasesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}