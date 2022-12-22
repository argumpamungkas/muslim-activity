package com.argumelar.muslimactivity.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumelar.muslimactivity.databinding.AdapterListDetailSurahBinding
import com.argumelar.muslimactivity.model.DataQuran
import com.argumelar.muslimactivity.model.DetailAyahs

class AdapterDetailSurah(private val dataQuran: ArrayList<DetailAyahs>) :
    RecyclerView.Adapter<AdapterDetailSurah.ViewHolder>() {

    class ViewHolder(val binding: AdapterListDetailSurahBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterListDetailSurahBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataQuran[position]

            holder.binding.tvAr.text = data.text!!.ar
            holder.binding.tvRead.text = data.number!!.insurah.toString() + ". " + data.text.read
            holder.binding.tvTranslation.text = data.number.insurah.toString() + ". " + data.translation!!.id

    }

    override fun getItemCount(): Int {
        return dataQuran.size
    }

    fun setData(newAyat: ArrayList<DetailAyahs>){
        dataQuran.clear()
        dataQuran.addAll(newAyat)
        notifyDataSetChanged()
    }


}