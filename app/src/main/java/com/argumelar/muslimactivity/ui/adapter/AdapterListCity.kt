package com.argumelar.muslimactivity.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumelar.muslimactivity.databinding.AdapterListCityBinding
import com.argumelar.muslimactivity.model.SemuaKota

class AdapterListCity(val dataCity: ArrayList<SemuaKota>, val listener : OnListener) :
    RecyclerView.Adapter<AdapterListCity.ViewHolder>() {

    class ViewHolder(val binding: AdapterListCityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterListCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kota = dataCity[position]
        holder.binding.tvNamaKota.text = kota.nama
        holder.itemView.setOnClickListener {
            listener.onClick(kota)
        }
    }

    override fun getItemCount(): Int {
        return dataCity.size
    }

    fun setListCity(newCity: ArrayList<SemuaKota>){
        dataCity.clear()
        dataCity.addAll(newCity)
        notifyDataSetChanged()
    }

    interface OnListener{
        fun onClick(kota: SemuaKota)
    }
}