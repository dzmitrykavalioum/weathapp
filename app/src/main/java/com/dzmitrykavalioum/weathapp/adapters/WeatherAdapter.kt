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

class WeatherAdapter(private val listItems: List<WeatherItem>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val iv_weather: ImageView = itemView.findViewById(R.id.iv_item_weather)
//        val tv_time: TextView = itemView.findViewById(R.id.tv_item_time)
//        val tv_description: TextView = itemView.findViewById(R.id.tv_item_description)
//        val tv_temp: TextView = itemView.findViewById(R.id.tv_item_temp)
//
        val ivWeather: ImageView = itemView.findViewById(R.id.iv_item_weather_n)
        val tvTime: TextView = itemView.findViewById(R.id.tv_item_date_time_n)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description_n)
        val tvTemp: TextView = itemView.findViewById(R.id.tv_item_temp_curr_n)
        val tvTempMax: TextView = itemView.findViewById(R.id.tv_item_temp_max_n)
        val tvTempMin: TextView = itemView.findViewById(R.id.tv_item_temp_min_n)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView =
            //LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
            LayoutInflater.from(parent.context).inflate(R.layout.item_wether_new, parent, false)

        return WeatherViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = listItems[position]
        val imageUrl = "http://openweathermap.org/img/wn/" + currentItem.weather[0].icon + "@2x.png"
        Picasso.get().load(imageUrl).into(holder.ivWeather)
        currentItem.weather[0].icon
        holder.tvTime.text = currentItem.dt_txt
        holder.tvDescription.text = currentItem.weather[0].description
        holder.tvTemp.text = currentItem.main.temp.toString()+"°"
        holder.tvTempMax.text = currentItem.main.temp_max.toString()+"°"
        holder.tvTempMin.text = currentItem.main.temp_min.toString()+"°"


    }

    override fun getItemCount() = listItems.size
}