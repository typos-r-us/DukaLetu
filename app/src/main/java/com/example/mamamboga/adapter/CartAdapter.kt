package com.example.mamamboga.adapter

import com.example.mamamboga.model.BucketModel
import com.example.mamamboga.utils.Constants.ONE
import com.example.mamamboga.utils.Constants.POINT_FIVE
import com.example.mamamboga.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart_layout.view.*


class CartAdapter(private val cartList: MutableList<BucketModel>, val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cl_main = view.cl_main
        val ivProduct = view.ivProduct
        val tvProductName = view.tvProductName
        val tvProductQuantity = view.tvProductQuantity
        val tvProductPrice = view.tvProductPrice
        val btnIncrement = view.btnIncrement
        val tvQuantity = view.tvQuantity
        val tvUnit = view.tvUnit
        val btnDecrement = view.btnDecrement
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cartList[position]
        var numberOfVeg = listItem.quantity
        val price = listItem.price

        Picasso.with(context).load(listItem.foodImage).placeholder(R.drawable.placeholder).into(holder.ivProduct)
        holder.tvProductName.text = listItem.foodName
        holder.tvProductQuantity.text = listItem.quantity.toString()
        holder.tvQuantity.text = listItem.quantity.toString()
        holder.tvProductPrice.text = context.getString(R.string.amount, listItem.amount.toString())

        holder.btnDecrement.setOnClickListener {

            if (numberOfVeg >= ONE) {
                numberOfVeg -= POINT_FIVE
            }

            if (numberOfVeg == POINT_FIVE) {
                holder.btnDecrement.visibility = View.GONE
            }
            holder.tvProductQuantity.text = numberOfVeg.toString()
            holder.tvQuantity.text = numberOfVeg.toString()
            val amountPrice = numberOfVeg * price
            holder.tvProductPrice.text = context.getString(R.string.amount, amountPrice.toString())
        }

        holder.btnIncrement.setOnClickListener {
            numberOfVeg += POINT_FIVE
            holder.btnDecrement.visibility = View.VISIBLE
            holder.tvProductQuantity.text = numberOfVeg.toString()
            holder.tvQuantity.text = numberOfVeg.toString()

            val amountPrice = numberOfVeg * price
            holder.tvProductPrice.text = context.getString(R.string.amount, amountPrice.toString())
        }

    }
}