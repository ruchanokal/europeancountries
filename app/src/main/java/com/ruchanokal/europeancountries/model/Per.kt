package com.ruchanokal.europeancountries.model


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
@SuppressLint("ParcelCreator")
data class Per(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}