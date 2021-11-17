package com.ascend.mobile.exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.application.app.lifecycle.ResourceLiveData
import com.ascend.mobile.exam.dao.ProductDao

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    var productListData = ResourceLiveData<List<ProductDao>>()
    var productData = ResourceLiveData<ProductDao>()

    fun getProductList() {
        ProductRepository().getProductList (productListData);
    }

    fun getProductDetail(id:Int) {
        ProductRepository().getProductDetail (productData,id);
    }

}