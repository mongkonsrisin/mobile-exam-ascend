package com.ascend.mobile.exam

import com.ascend.mobile.exam.dao.ProductDao

interface ProductListener {
    fun onProductClick(data: ProductDao)
}