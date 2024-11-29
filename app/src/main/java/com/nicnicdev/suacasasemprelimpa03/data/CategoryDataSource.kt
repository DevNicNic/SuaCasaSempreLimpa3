package com.nicnicdev.suacasasemprelimpa03.data

import com.nicnicdev.suacasasemprelimpa03.R
import com.nicnicdev.suacasasemprelimpa03.domain.model.Category

class CategoryDataSource {
    fun getCategories(): List<Category> {
        return listOf(
            Category("Cozinha", R.drawable.cozinha),
            Category("Sala", R.drawable.sala),
            Category("Banheiro", R.drawable.banheiro),
            Category("Quarto", R.drawable.quarto),
            Category("Lavanderia", R.drawable.lavanderia),
        )
    }
}