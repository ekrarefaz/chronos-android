package com.icedtea.chronos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WatchDataClass(
    val watchCode:String = "",
    val watchBrand:String = "",
    val watchName: String = "",
    val watchDiaSize: String = "",
    val watchDialColor: String = "",
    val watchPrice: String = ""
    ) : Parcelable