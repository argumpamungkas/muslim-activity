package com.argumelar.muslimactivity.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumelar.muslimactivity.databinding.AdapterListSurahBinding
import com.argumelar.muslimactivity.model.QuranModel

class AdapterListSurah(private val dataQurans: ArrayList<QuranModel>, val listener: OnAdapterListener) :
    RecyclerView.Adapter<AdapterListSurah.ViewHolder>() {

    class ViewHolder(val binding: AdapterListSurahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterListSurahBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataQurans[position]
        holder.binding.tvNumberSurah.text = data.number.toString()
        holder.binding.tvNameSurah.text = data.asma!!.id!!.short
        holder.binding.tvTranslation.text = data.asma.translation!!.id + " (" + data.ayahCount.toString() + ")"
        holder.binding.tvNameSurahArab.text = data.asma.ar!!.short
        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }
    }

    override fun getItemCount(): Int {
        return dataQurans.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newQuran: ArrayList<QuranModel>) {
        dataQurans.clear()
        dataQurans.addAll(newQuran)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(qurans: QuranModel)
    }

}