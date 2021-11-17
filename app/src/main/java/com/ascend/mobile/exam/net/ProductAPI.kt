package com.application.data.net

import com.ascend.mobile.exam.dao.ProductDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {

    @GET("products")
    fun getProductList(): Call<List<ProductDao>>

    @GET("products/{id}")
    fun getProductDetail(@Path("id") id:Int): Call<ProductDao>

}