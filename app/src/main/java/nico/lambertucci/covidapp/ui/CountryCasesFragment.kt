package nico.lambertucci.covidapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.world_cases_fragment.*
import kotlinx.coroutines.launch
import nico.lambertucci.covidapp.R
import nico.lambertucci.covidapp.di.Injection
import nico.lambertucci.covidapp.ui.adapters.CountryItemAdapter
import nico.lambertucci.covidapp.ui.utils.viewUtils
import nico.lambertucci.covidapp.ui.viewmodel.CountryCasesViewModel

class CountryCasesFragment : Fragment(), viewUtils {

    private lateinit var viewModel: CountryCasesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var loading: TextView
    private lateinit var toolbar: Toolbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.country_cases_fragment, container, false)
        progressBar = view.findViewById(R.id.countryLoading)
        loading = view.findViewById(R.id.loadingText)
        toolbar = view.findViewById(R.id.countryToolbar)
        setupToolbar(toolbar)
        showLoadingBar()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            Injection.getViewModelFactory()
        ).get(CountryCasesViewModel::class.java)
        setupBottomNavigation()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupView()
    }


    private fun setupView() = lifecycleScope.launch {
        viewManager = LinearLayoutManager(requireContext())
        viewModel.getCovidByCountry()?.observe(viewLifecycleOwner, Observer {
            viewAdapter = CountryItemAdapter(it)
            recyclerView = requireView().findViewById<RecyclerView>(R.id.countryView).apply {
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
                layoutManager = viewManager
                adapter = viewAdapter
            }
        })
        hideLoadingBar()

    }

    override fun showLoadingBar() {
        progressBar.visibility = View.VISIBLE
        loading.visibility = View.VISIBLE
    }

    override fun hideLoadingBar() {
        progressBar.visibility = View.GONE
        loading.visibility = View.GONE
    }

    override fun setupToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            setHasOptionsMenu(true)
        }
        toolbar.apply {
            title = "Estado de países"
            setNavigationIcon(R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.worldCasesFragment)
            }
        }
    }

    private fun setupBottomNavigation() {

        bottomCountryNavigationView.selectedItemId =
            R.id.countries

        bottomCountryNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    findNavController().navigate(R.id.worldCasesFragment)
                    true
                }

                R.id.countries -> {
                    true
                }
                else -> false
            }
        }
    }
}

