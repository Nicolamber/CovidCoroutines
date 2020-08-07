package nico.lambertucci.covidapp.domain.data.main


import com.google.gson.annotations.SerializedName

data class WorldCases(
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int
)