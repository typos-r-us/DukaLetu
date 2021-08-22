package com.example.mamamboga.adapter

import com.example.mamamboga.R
import com.example.mamamboga.activity.DetailActivity
import com.example.mamamboga.model.FoodDetailModel
import com.example.mamamboga.utils.Constants.ADVANTAGE
import com.example.mamamboga.utils.Constants.DISEASE_HEAL
import com.example.mamamboga.utils.Constants.FOOD_NAME
import com.example.mamamboga.utils.Constants.IMAGE_URL
import com.example.mamamboga.utils.Constants.PRECAUTIONS
import com.example.mamamboga.utils.Constants.PRICE
import com.example.mamamboga.utils.Constants.VITAMINS
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CategoryItemAdapter (val itemList: List<FoodDetailModel>, val context: Context) : RecyclerView.Adapter<CategoryItemAdapter.MyViewHolder>() {

    class MyViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var tvName: TextView = parent.findViewById(R.id.tv_name)
        var tvNameHindi: TextView = parent.findViewById(R.id.tv_local_name)
        var ivFood: ImageView = parent.findViewById(R.id.iv_food)
        var main: LinearLayout = parent.findViewById(R.id.llMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val row = itemList[position]
        holder.tvName.text = row.foodName
        val typeface = ResourcesCompat.getFont(context, R.font.krutihindi)
        holder.tvNameHindi.typeface = typeface
        holder.tvNameHindi.text = row.localFoodName
        Picasso.with(context).load(row.foodImage).placeholder(R.drawable.placeholder).into(holder.ivFood)
        holder.main.setOnClickListener {
            Log.e("Position adapter:", position.toString())
            val intent = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(FOOD_NAME, row.foodName)
            bundle.putString(IMAGE_URL, row.foodImage)
            bundle.putString(ADVANTAGE, row.advantages)
            bundle.putString(VITAMINS, row.vitamins)
            bundle.putString(DISEASE_HEAL, row.diseaseHeal)
            bundle.putString(PRECAUTIONS, row.precaution)
            bundle.putString(PRICE, row.price)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}