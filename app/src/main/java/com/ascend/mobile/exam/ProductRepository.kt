package com.ascend.mobile.exam

import android.util.Log
import com.application.app.lifecycle.LiveDataCallback
import com.application.app.lifecycle.ResourceLiveData
import com.application.data.net.ProductAPI
import com.application.data.net.ServiceFactory
import com.ascend.mobile.exam.dao.ProductDao
import com.google.gson.Gson

class ProductRepository {

    val LOG_WS = "LogAscend"


    private val productAPI = ServiceFactory.create(ProductAPI::class.java)

    fun getProductList(liveData: ResourceLiveData<List<ProductDao>>) {

        liveData.postIsLoading()
        productAPI.getProductList().enqueue(object : LiveDataCallback<List<ProductDao>>(liveData) {
            override fun onSuccess(result: List<ProductDao>?) {
                super.onSuccess(result)
                Log.i(
                    LOG_WS,
                    "onSuccess (products) -> ${Gson().toJson(result)}"
                )
            }

            override fun onError(throwable: Throwable) {
                super.onError(throwable)
                Log.e(LOG_WS, "onError (products)", throwable)

            }
        })
    }


    fun getProductDetail(liveData: ResourceLiveData<ProductDao>, id:Int) {

        liveData.postIsLoading()
        productAPI.getProductDetail(id).enqueue(object : LiveDataCallback<ProductDao>(liveData) {
            override fun onSuccess(result: ProductDao?) {
                super.onSuccess(result)
                Log.i(
                    LOG_WS,
                    "onSuccess (products/$id) -> ${Gson().toJson(result)}"
                )
            }

            override fun onError(throwable: Throwable) {
                super.onError(throwable)
                Log.e(LOG_WS, "onError (products/$id)", throwable)
            }
        })
    }

}