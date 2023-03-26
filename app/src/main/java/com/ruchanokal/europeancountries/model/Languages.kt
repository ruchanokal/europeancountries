package com.ruchanokal.europeancountries.model


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Languages(
    @SerializedName("ron")
    val ron: String,
    @SerializedName("bos")
    val bos: String,
    @SerializedName("hrv")
    val hrv: String,
    @SerializedName("srp")
    val srp: String,
    @SerializedName("lav")
    val lav: String,
    @SerializedName("ell")
    val ell: String,
    @SerializedName("bel")
    val bel: String,
    @SerializedName("eng")
    val eng: String,
    @SerializedName("mlt")
    val mlt: String,
    @SerializedName("gle")
    val gle: String,
    @SerializedName("fin")
    val fin: String,
    @SerializedName("swe")
    val swe: String,
    @SerializedName("sqi")
    val sqi: String,
    @SerializedName("cnr")
    val cnr: String,
    @SerializedName("cat")
    val cat: String,
    @SerializedName("nor")
    val nor: String,
    @SerializedName("bul")
    val bul: String,
    @SerializedName("fra")
    val fra: String,
    @SerializedName("nfr")
    val nfr: String,
    @SerializedName("ces")
    val ces: String,
    @SerializedName("slk")
    val slk: String,
    @SerializedName("ita")
    val ita: String,
    @SerializedName("tur")
    val tur: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("mkd")
    val mkd: String,
    @SerializedName("hun")
    val hun: String,
    @SerializedName("deu")
    val deu: String,
    @SerializedName("nno")
    val nno: String,
    @SerializedName("nob")
    val nob: String,
    @SerializedName("smi")
    val smi: String,
    @SerializedName("spa")
    val spa: String,
    @SerializedName("gsw")
    val gsw: String,
    @SerializedName("roh")
    val roh: String,
    @SerializedName("ukr")
    val ukr: String,
    @SerializedName("nld")
    val nld: String,
    @SerializedName("est")
    val est: String,
    @SerializedName("pol")
    val pol: String,
    @SerializedName("ltz")
    val ltz: String,
    @SerializedName("de")
    val de: String,
    @SerializedName("rus")
    val rus: String,
    @SerializedName("isl")
    val isl: String,
    @SerializedName("nrf")
    val nrf: String,
    @SerializedName("dan")
    val dan: String,
    @SerializedName("fao")
    val fao: String,
    @SerializedName("slv")
    val slv: String,
    @SerializedName("lit")
    val lit: String,
    @SerializedName("por")
    val por: String,
    @SerializedName("glv")
    val glv: String

) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}