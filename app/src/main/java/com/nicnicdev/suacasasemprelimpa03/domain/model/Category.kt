package com.nicnicdev.suacasasemprelimpa03.domain.model

import androidx.annotation.DrawableRes


data class Category(
    val name: String,
    @DrawableRes val imageRes: Int
)