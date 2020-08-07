package nico.lambertucci.covidapp.domain.data.countries


import com.google.gson.annotations.SerializedName

data class CountryResponseItem(
    @SerializedName("active")
    val active: Int,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Int,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("totalTests")
    val totalTests: Int
)