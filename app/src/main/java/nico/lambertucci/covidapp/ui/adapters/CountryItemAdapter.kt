package nico.lambertucci.covidapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nico.lambertucci.covidapp.R
import nico.lambertucci.covidapp.domain.data.countries.CountryResponse
import nico.lambertucci.covidapp.domain.data.countries.CountryResponseItem

class CountryItemAdapter(
    private val countries: CountryResponse
) : RecyclerView.Adapter<CountryItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        )
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = countries[position]
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryName = itemView.findViewById<TextView>(R.id.countryName)
        private val countryCases = itemView.findViewById<TextView>(R.id.todayCases)
        private val totalCases = itemView.findViewById<TextView>(R.id.totalCases)
        private val activeCountryCases = itemView.findViewById<TextView>(R.id.activeCountryCases)
        private val recovered = itemView.findViewById<TextView>(R.id.countryRecovered)
        private val totalDeaths = itemView.findViewById<TextView>(R.id.countryDeath)

        fun bind(item: CountryResponseItem){
            countryName.text = item.country
            countryCases.text = "Casos al día de hoy: ${item.todayCases}"
            totalCases.text = "Casos totales: ${item.cases}"
            activeCountryCases.text = "Casos Activos: ${item.active}"
            recovered.text = "Total de Recuperados: ${item.recovered}"
            totalDeaths.text = "Muertes al dia de hoy: ${item.todayDeaths} | Total de muertes: ${item.deaths}"

        }
    }
}