package nico.lambertucci.covidapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.world_cases_fragment.*
import kotlinx.coroutines.launch
import nico.lambertucci.covidapp.R
import nico.lambertucci.covidapp.di.Injection
import nico.lambertucci.covidapp.ui.utils.viewUtils
import nico.lambertucci.covidapp.ui.viewmodel.WorldCasesViewModel

class WorldCasesFragment : Fragment(), viewUtils {

    private lateinit var deaths: TextView
    private lateinit var recovereds: TextView
    private lateinit var actives: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var covidImage: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingText: TextView

    private lateinit var viewModel: WorldCasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.world_cases_fragment, container, false)
        deaths = view.findViewById(R.id.deathsValue)
        recovereds = view.findViewById(R.id.recoveredValue)
        actives = view.findViewById(R.id.activeCasesValue)
        toolbar = view.findViewById(R.id.mainToolbar)
        covidImage = view.findViewById(R.id.covidMainImage)
        progressBar = view.findViewById(R.id.mainProgressBar)
        loadingText = view.findViewById(R.id.mainLoading)
        showLoadingBar()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            Injection.getViewModelFactory()
        ).get(WorldCasesViewModel::class.java)

        covidImage.setImageResource(R.drawable.covidlogo)

        toolbar.apply {
            title = getString(R.string.main)
            setHasOptionsMenu(true)
        }

        setupBottomNavigation()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupView()
    }

    private fun setupView() = lifecycleScope.launch {
        viewModel.getAllCases()?.observe(viewLifecycleOwner, Observer {

            deaths.text = "Muertes: ${it.deaths}"
            recovereds.text = "Recuperados: ${it.recovered}"
            actives.text = "Casos Activos: ${it.cases}"
        })
        hideLoadingBar()
    }


    private fun setupBottomNavigation() {

        bottomCountryNavigationView.selectedItemId =
            R.id.main

        bottomCountryNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    true
                }

                R.id.countries -> {
                   findNavController().navigate(R.id.CountryCasesFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun showLoadingBar() {
        progressBar.visibility = View.VISIBLE
        loadingText.visibility = View.VISIBLE
        deaths.visibility = View.GONE
        recovereds.visibility = View.GONE
        actives.visibility = View.GONE
    }

    override fun hideLoadingBar() {
        progressBar.visibility = View.GONE
        loadingText.visibility = View.GONE
        deaths.visibility = View.VISIBLE
        recovereds.visibility = View.VISIBLE
        actives.visibility = View.VISIBLE
    }

    override fun setupToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            setHasOptionsMenu(true)
        }
        toolbar.apply {
            title = "Inicio"
        }
    }


}