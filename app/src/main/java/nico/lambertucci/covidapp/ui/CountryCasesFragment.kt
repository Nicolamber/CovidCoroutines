package nico.lambertucci.covidapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import nico.lambertucci.covidapp.R
import nico.lambertucci.covidapp.di.Injection
import nico.lambertucci.covidapp.ui.adapters.CountryItemAdapter
import nico.lambertucci.covidapp.ui.viewmodel.CountryCasesViewModel

class CountryCasesFragment : Fragment() {

    private lateinit var viewModel: CountryCasesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_cases_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,Injection.getViewModelFactory()).get(CountryCasesViewModel::class.java)


        setupView()
    }


    private fun setupView()= lifecycleScope.launch{
        viewManager = LinearLayoutManager(requireContext())
        viewModel.getCovidByCountry()?.observe(viewLifecycleOwner, Observer {
            viewAdapter = CountryItemAdapter(it)
            recyclerView = requireView().findViewById<RecyclerView>(R.id.countryView).apply{
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
                layoutManager = viewManager
                adapter = viewAdapter
            }
        })

    }
}