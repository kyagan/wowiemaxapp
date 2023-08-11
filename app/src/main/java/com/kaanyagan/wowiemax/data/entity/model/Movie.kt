package com.kaanyagan.wowiemax.data.entity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val id:Int,
    val name:Int,
    val duration:String,
    val image:String,
    val producer:String,
    val director:String,
    val scriptwriter:String,
    val rateOfLike:Int,
    val categories:List<Categorie>,
    val content:Int,
    val ageLimit:Int,
    val isViolence:Boolean = false,
    val isNegativeExample:Boolean = false,
    var numberStar:Int
    ): Parcelable