package com.example.productsapplication.ui.fragments
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.productsapplication.R
import com.example.productsapplication.data.model.Product


class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Inflate the item layout for each product (item_product.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    // Bind product data to each view
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
        val animationRes = if (position % 2 == 0) R.anim.item_slide_right else R.anim.item_slide_left
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, animationRes)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int = productList.size

    // ViewHolder class holding views for each product item
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.itemProductImage)
        private val productName: TextView = itemView.findViewById(R.id.itemProductName)
        private val productPrice: TextView = itemView.findViewById(R.id.itemProductPrice)

        fun bind(product: Product) {
            productName.text = product.title
            productPrice.text = "₹${product.price}"

            // Load image using Coil
            productImage.load(product.image) {
                placeholder(android.R.drawable.ic_menu_report_image)
                error(android.R.drawable.stat_notify_error)
                crossfade(true)
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        holder.itemView.clearAnimation()
        super.onViewDetachedFromWindow(holder)
    }

}