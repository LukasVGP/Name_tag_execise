package com.example.class3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String = "",
    val age: Int = 0,
    val country: String = "",
    val position: String = "",
    val schoolName: String = "",
    val id: Int = 0
) : Parcelable
