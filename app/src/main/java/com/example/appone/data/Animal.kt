package com.example.appone.data
import androidx.annotation.DrawableRes

enum class AnimalType{
    CAT,
    DOG,
    CAT_AND_DOGS,
}

data class Animal (
    @DrawableRes val image: Int,
     val title: String,
     val age: Int,
     val type: AnimalType
    )
