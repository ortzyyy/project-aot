package com.orryfrasetyo.projectaot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Aot(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
