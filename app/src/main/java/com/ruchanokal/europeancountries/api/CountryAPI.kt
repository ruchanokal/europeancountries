package com.ruchanokal.europeancountries.api

import com.ruchanokal.europeancountries.model.CountryItem
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //https://restcountries.com/v3.1/region/europe

    @GET("https://restcountries.com/v3.1/region/europe")
    fun getCountryListData() : Single<List<CountryItem>>

}