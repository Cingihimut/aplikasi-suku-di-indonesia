package com.example.androidsubmission

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Suku(
    val id: Int,
    val name: String,
    val description: String,
    val photo: Int
): Parcelable
