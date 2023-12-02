package com.example.coursesgridapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val associatedCourse: Int,
    @DrawableRes val imageResourceId : Int
)
