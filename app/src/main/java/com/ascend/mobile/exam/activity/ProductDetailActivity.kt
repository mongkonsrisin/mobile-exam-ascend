package com.ascend.mobile.exam.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.application.app.lifecycle.ServiceCallObserver
import com.application.app.lifecycle.viewModels
import com.ascend.mobile.exam.ErrorDialog
import com.ascend.mobile.exam.ProductViewModel
import com.ascend.mobile.exam.R
import com.ascend.mobile.exam.ViewUtils
import com.ascend.mobile.exam.dao.ProductDao
import com.ascend.mobile.exam.databinding.ActivityProductDetailBinding
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {

    private val binding: ActivityProductDetailBinding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<ProductViewModel>()

    companion object {
        private const val EXTRA_ID = "id"

        fun createIntent(
            context: Context,
            id:Int?
        ): Intent {
            return Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handleBackButton()
        attachObserver()

        intent.getIntExtra(EXTRA_ID,0).also {
            viewModel.getProductDetail(it)
        }
    }

    private fun handleBackButton() {
        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun attachObserver() {
        viewModel.productData.observe(
            this,
            object : ServiceCallObserver<ProductDao>() {
                override fun postIsLoading(value: Boolean) {
                    ViewUtils.showLoading(this@ProductDetailActivity, isShow = value)
                }

                override fun postValue(value: ProductDao?) {
                    value?.also {
                        binding.productName.text = value.title
                        binding.productDescription.text = value.content
                        binding.productPrice.text = String.format("%.2f", value.price)
                        binding.newProduct.visibility = if (value.isNewProduct == true) {
                            View.VISIBLE
                        } else {
                            View.INVISIBLE
                        }

                        Glide
                            .with(this@ProductDetailActivity)
                            .load(value.image)
                            .placeholder(R.drawable.no_image)
                            .fitCenter()
                            .into(binding.productImage)
                    }
                }

                override fun postError(throwable: Throwable?) {
                    ErrorDialog.Builder(this@ProductDetailActivity)
                        .withTitle("Error")
                        .withBody("Please try again.")
                        .withButton("OK")
                        .withOnButtonClick { d ->
                            d.dismiss()
                            finish()
                        }
                        .show()
                }

            })
    }

}