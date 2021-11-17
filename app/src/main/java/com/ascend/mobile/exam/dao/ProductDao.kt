package com.ascend.mobile.exam.dao

import com.google.gson.annotations.SerializedName


data class ProductDao(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("content")
    val content: String? = null,

    @SerializedName("isNewProduct")
    val isNewProduct: Boolean? = null,

    @SerializedName("price")
    val price: Double? = null,
)