package com.ruchanokal.europeancountries.model


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class CountryItem (
    @SerializedName("altSpellings")
    val altSpellings: List<String>,
    @SerializedName("area")
    val area: Double,
    @SerializedName("borders")
    val borders: List<String>,
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo,
    @SerializedName("car")
    val car: Car,
    @SerializedName("cca2")
    val cca2: String,
    @SerializedName("cca3")
    val cca3: String,
    @SerializedName("ccn3")
    val ccn3: String,
    @SerializedName("cioc")
    val cioc: String,
    @SerializedName("coatOfArms")
    val coatOfArms: CoatOfArms,
    @SerializedName("continents")
    val continents: List<String>,
    @SerializedName("currencies")
    val currencies: Currencies,
    @SerializedName("demonyms")
    val demonyms: Demonyms,
    @SerializedName("fifa")
    val fifa: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("gini")
    val gini: Gini,
    @SerializedName("idd")
    val idd: Idd,
    @SerializedName("independent")
    val independent: Boolean,
    @SerializedName("landlocked")
    val landlocked: Boolean,
    @SerializedName("languages")
    val languages: Languages,
    @SerializedName("latlng")
    val latlng: List<Double>,
    @SerializedName("maps")
    val maps: Maps,
    @SerializedName("name")
    val name: Name,
    @SerializedName("population")
    val population: Int,
    @SerializedName("postalCode")
    val postalCode: PostalCode,
    @SerializedName("region")
    val region: String,
    @SerializedName("startOfWeek")
    val startOfWeek: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subregion")
    val subregion: String,
    @SerializedName("timezones")
    val timezones: List<String>,
    @SerializedName("tld")
    val tld: List<String>,
    @SerializedName("translations")
    val translations: Translations,
    @SerializedName("unMember")
    val unMember: Boolean
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}