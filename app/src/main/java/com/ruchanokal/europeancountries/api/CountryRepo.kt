package com.ruchanokal.europeancountries.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ruchanokal.europeancountries.model.CountryItem
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryRepo @Inject constructor( val api : CountryAPI) {

    private val TAG = "CountryRepo"

    fun getCountryListData() : Single<List<CountryItem>> {
        return api.getCountryListData()
    }


    //getting country data from API
    fun makeApiCall(compositeDisposable: CompositeDisposable,
                     loadingData: MutableLiveData<Boolean>,
                     countryData: MutableLiveData<List<CountryItem>>,
                     errorData: MutableLiveData<Boolean>) {

        Log.e(TAG,"get search country datas")

        loadingData.postValue(true)

        compositeDisposable.add(getCountryListData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CountryItem>>(){
                override fun onSuccess(t: List<CountryItem>) {

                    Log.i(TAG,"onSuccess")
                    loadingData.postValue(false)
                    errorData.postValue(false)
                    countryData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG,"error: " + e.localizedMessage)
                    loadingData.postValue(false)
                    errorData.postValue(true)
                }

            }))


    }


}