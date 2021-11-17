package com.ascend.mobile.exam.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.app.lifecycle.ServiceCallObserver
import com.application.app.lifecycle.viewModels
import com.ascend.mobile.exam.*
import com.ascend.mobile.exam.dao.ProductDao
import com.ascend.mobile.exam.databinding.ActivityProductListBinding
import com.ascend.mobile.exam.databinding.ItemProductBinding
import com.bumptech.glide.Glide

class ProductListActivity : AppCompatActivity(), ProductListener {

    private val binding: ActivityProductListBinding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<ProductViewModel>()
    private var productAdapter: ProductAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupAdapter()
        attachObserver()
        viewModel.getProductList()


    }


    private fun setupAdapter() {
        productAdapter = ProductAdapter(this)
        binding.productList.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = productAdapter
            onFlingListener = null
        }

    }

    private fun attachObserver() {
        viewModel.productListData.observe(
            this,
            object : ServiceCallObserver<List<ProductDao>>() {
                override fun postIsLoading(value: Boolean) {
                    ViewUtils.showLoading(this@ProductListActivity, isShow = value)
                }

                override fun postValue(value: List<ProductDao>?) {
                    value?.also {
                        productAdapter?.setData(it)
                    }
                }

                override fun postError(throwable: Throwable?) {
                    ErrorDialog.Builder(this@ProductListActivity)
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


    //adapter & view holder
    private inner class ProductAdapter(
        private val listener: ProductListener
    ) : RecyclerView.Adapter<MenuViewHolder>() {

        private var data = listOf<ProductDao>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            return MenuViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            )
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
            holder.bind(data[position])
            holder.itemView.setOnClickListener {
                listener.onProductClick(data[position])
            }
        }

        fun setData(data: List<ProductDao>) {
            this.data = data
            notifyDataSetChanged()
        }
    }

    private inner class MenuViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {

        private val binding = ItemProductBinding.bind(itemsView)

        fun bind(data: ProductDao) {
            itemView.apply {
                binding.productName.text = data.title
                binding.productPrice.text = String.format("%.2f", data.price)
                binding.newProduct.visibility = if (data.isNewProduct == true) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }


                Glide
                    .with(context)
                    .load(data.image)
                    .placeholder(R.drawable.no_image)
                    .fitCenter()
                    .into(binding.productImage)
            }
        }
    }

    override fun onProductClick(data: ProductDao) {
        val intent = ProductDetailActivity.createIntent(this, data.id)
        startActivity(intent)
    }


}