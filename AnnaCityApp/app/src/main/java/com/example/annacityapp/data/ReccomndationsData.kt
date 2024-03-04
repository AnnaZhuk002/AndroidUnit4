package com.example.annacityapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.annacityapp.R

data class Recommendation(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val category: Int,
    @StringRes val address: Int,
    @StringRes val description: Int,
    @DrawableRes val imageResourceId: Int,
)
object ReccomndationsData {
    val recommendations: List<Recommendation> = listOf(
        Recommendation(
            id = 1,
            name = R.string.cat1_0,
            category = R.string.category_1,
            address = R.string.cat1_0_add,
            description = R.string.cat1_0_desc,
            imageResourceId = R.drawable.p1
        ),
        Recommendation(
            id = 2,
            name = R.string.cat1_1,
            category = R.string.category_1,
            address = R.string.cat1_1_add,
            description = R.string.cat1_1_desc,
            imageResourceId = R.drawable.p2
        ),
        Recommendation(
            id = 3,
            name = R.string.cat1_2,
            category = R.string.category_1,
            address = R.string.cat1_2_add,
            description = R.string.cat1_2_desc,
            imageResourceId = R.drawable.p3
        ),
        Recommendation(
            id = 4,
            name = R.string.cat2_0,
            category = R.string.category_2,
            address = R.string.cat2_0_add,
            description = R.string.cat2_0_desc,
            imageResourceId = R.drawable.p4
        ),
        Recommendation(
            id = 5,
            name = R.string.cat2_1,
            category = R.string.category_2,
            address = R.string.cat2_1_add,
            description = R.string.cat2_1_desc,
            imageResourceId = R.drawable.p5
        ),
        Recommendation(
            id = 6,
            name = R.string.cat2_2,
            category = R.string.category_2,
            address = R.string.cat2_2_add,
            description = R.string.cat2_2_desc,
            imageResourceId = R.drawable.p6
        ),
        Recommendation(
            id = 7,
            name = R.string.cat3_0,
            category = R.string.category_3,
            address = R.string.cat3_0_add,
            description = R.string.cat3_0_desc,
            imageResourceId = R.drawable.p7
        ),
        Recommendation(
            id = 8,
            name = R.string.cat3_1,
            category = R.string.category_3,
            address = R.string.cat3_1_add,
            description = R.string.cat3_1_desc,
            imageResourceId = R.drawable.p8
        ),
        Recommendation(
            id = 9,
            name = R.string.cat3_2,
            category = R.string.category_3,
            address = R.string.cat3_2_add,
            description = R.string.cat3_2_desc,
            imageResourceId = R.drawable.p9
        ),
    )
}