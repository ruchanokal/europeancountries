package com.ruchanokal.europeancountries.model


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Translations(
    @SerializedName("ara")
    val ara: Ara,
    @SerializedName("bre")
    val bre: Bre,
    @SerializedName("ces")
    val ces: Ces,
    @SerializedName("cym")
    val cym: Cym,
    @SerializedName("deu")
    val deu: Deu,
    @SerializedName("est")
    val est: Est,
    @SerializedName("fin")
    val fin: Fin,
    @SerializedName("fra")
    val fra: FraX,
    @SerializedName("hrv")
    val hrv: Hrv,
    @SerializedName("hun")
    val hun: Hun,
    @SerializedName("ita")
    val ita: Ita,
    @SerializedName("jpn")
    val jpn: Jpn,
    @SerializedName("kor")
    val kor: Kor,
    @SerializedName("nld")
    val nld: Nld,
    @SerializedName("per")
    val per: Per,
    @SerializedName("pol")
    val pol: Pol,
    @SerializedName("por")
    val por: Por,
    @SerializedName("rus")
    val rus: Rus,
    @SerializedName("slk")
    val slk: Slk,
    @SerializedName("spa")
    val spa: Spa,
    @SerializedName("srp")
    val srp: Srp,
    @SerializedName("swe")
    val swe: Swe,
    @SerializedName("tur")
    val tur: Tur,
    @SerializedName("urd")
    val urd: Urd,
    @SerializedName("zho")
    val zho: Zho
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}