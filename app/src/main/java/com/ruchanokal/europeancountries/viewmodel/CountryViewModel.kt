package com.ruchanokal.europeancountries.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruchanokal.europeancountries.api.CountryRepo
import com.ruchanokal.europeancountries.model.CountryItem
import com.ruchanokal.europeancountries.util.Constants.Companion.allcountries
import com.ruchanokal.europeancountries.util.Constants.Companion.central
import com.ruchanokal.europeancountries.util.Constants.Companion.eastern
import com.ruchanokal.europeancountries.util.Constants.Companion.northern
import com.ruchanokal.europeancountries.util.Constants.Companion.southeast
import com.ruchanokal.europeancountries.util.Constants.Companion.southern
import com.ruchanokal.europeancountries.util.Constants.Companion.western
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class CountryViewModel @Inject constructor(val repo : CountryRepo)
    : ViewModel() {


    private val TAG = "CountryViewModel"
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Boolean>()
    val countryData = MutableLiveData<List<CountryItem>>()
    val tempCountryData = MutableLiveData<List<CountryItem>>()
    val filteredCountryData = MutableLiveData<List<CountryItem>>()

    val tempCountryList = arrayListOf<CountryItem>()
    var filteredCountryList = arrayListOf<CountryItem>()

    private val compositeDisposable  = CompositeDisposable()


    fun getCountryListData() {
        repo.makeApiCall(compositeDisposable,loadingLiveData,countryData,errorLiveData)
    }

    fun searchCountries(text : String?, countryList : List<CountryItem>) {

        tempCountryList.clear()

        val searchText = text?.lowercase(Locale.getDefault())

        if (searchText!!.isNotEmpty()) {

            countryList.forEach {
                if (it.name.official.lowercase(Locale.getDefault()).contains(searchText)){
                    tempCountryList.add(it)
                }
            }
            tempCountryData.value = tempCountryList
        } else {
            tempCountryList.clear()
            countryList.let { tempCountryList.addAll(it) }
            tempCountryData.value = tempCountryList
        }

    }

    fun filterCountries(filterText : String?, countryList : List<CountryItem>) {

        filteredCountryList.clear()

        when(filterText){

            southeast -> {
                for (country in countryList){
                    if (country.subregion.equals(southeast)){
                        filteredCountryList.add(country)
                    }
                }
            }

            southern -> {
                for (country in countryList){
                    if (country.subregion.equals(southern)){
                        filteredCountryList.add(country)
                    }
                }
            }

            western -> {
                for (country in countryList){
                    if (country.subregion.equals(western)){
                        filteredCountryList.add(country)
                    }
                }
            }

            eastern -> {
                for (country in countryList){
                    if (country.subregion.equals(eastern)){
                        filteredCountryList.add(country)

                    }
                }
            }

            central -> {
                for (country in countryList){
                    if (country.subregion.equals(central)){
                        filteredCountryList.add(country)

                    }
                }
            }

            northern -> {
                for (country in countryList){
                    if (country.subregion.equals(northern)){
                        filteredCountryList.add(country)
                    }
                }
            }

            allcountries -> {
                filteredCountryList.addAll(countryList)
            }
            else ->{
                filteredCountryList.addAll(countryList)
            }

        }

        filteredCountryData.value = filteredCountryList
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}