package com.dzmitrykavalioum.weathapp.adapters

import WeatherItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dzmitrykavalioum.weathapp.R
import com.squareup.picasso.Picasso
import java.util.*

class WeatherAdapter(private val listItems: List<WeatherItem>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_weather: ImageView = itemView.findViewById(R.id.iv_item_weather)
        val tv_time: TextView = itemView.findViewById(R.id.tv_item_time)
        val tv_description: TextView = itemView.findViewById(R.id.tv_item_description)
        val tv_temp: TextView = itemView.findViewById(R.id.tv_item_temp)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = listItems[position]
        val imageUrl = "http://openweathermap.org/img/wn/" + currentItem.weather[0].icon + "@2x.png"
        Picasso.get().load(imageUrl).into(holder.iv_weather)
        currentItem.weather[0].icon
        holder.tv_time.setText(currentItem.dt_txt)
        holder.tv_description.setText(currentItem.weather[0].description)
        holder.tv_temp.setText(currentItem.main.temp.toString())


    }

    override fun getItemCount() = listItems.size
}