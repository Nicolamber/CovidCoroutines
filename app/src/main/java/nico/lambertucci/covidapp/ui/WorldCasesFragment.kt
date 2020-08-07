package nico.lambertucci.covidapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.world_cases_fragment.*
import kotlinx.coroutines.launch
import nico.lambertucci.covidapp.R
import nico.lambertucci.covidapp.di.Injection
import nico.lambertucci.covidapp.ui.viewmodel.WorldCasesViewModel

class WorldCasesFragment : Fragment() {

    private lateinit var deaths: TextView
    private lateinit var recovereds: TextView
    private lateinit var actives: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var covidImage: ImageView

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

        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            setHasOptionsMenu(true)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            Injection.getViewModelFactory()
        ).get(WorldCasesViewModel::class.java)

        covidImage.setImageResource(R.drawable.covidlogo)
        setupToolbar(toolbar)
        setupBottomNavigation()
        getWorldCases()
    }

    private fun getWorldCases() = lifecycleScope.launch {
        viewModel.getAllCases()?.observe(viewLifecycleOwner, Observer {

            deaths.text = it.deaths.toString()
            recovereds.text = it.recovered.toString()
            actives.text = it.cases.toString()
        })
    }


    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.apply {
            title = getString(R.string.main)
            setHasOptionsMenu(true)
        }
    }

    private fun setupBottomNavigation() {

        bottomNavigationView.selectedItemId =
            R.id.main

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    true
                }

                R.id.countries -> {
                   // findNavController().navigate(R.id.countryFragment)
                    true
                }
                else -> false
            }
        }
    }
}