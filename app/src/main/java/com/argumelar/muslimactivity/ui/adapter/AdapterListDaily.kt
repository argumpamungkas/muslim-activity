package com.argumelar.muslimactivity.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.argumelar.muslimactivity.databinding.AdapterListDailyBinding
import com.argumelar.muslimactivity.model.DailyPrayersModel

class AdapterListDaily(val dataDaily: ArrayList<DailyPrayersModel>) :
    RecyclerView.Adapter<AdapterListDaily.ViewHolder>() {

    class ViewHolder(val binding: AdapterListDailyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterListDailyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val daily = dataDaily[position]
        holder.binding.tvNameDoa.text = daily.id.toString() + ". " + daily.doa
        holder.binding.tvAyat.text = daily.ayat
        holder.binding.tvLatin.text = daily.latin
        holder.binding.tvArtinya.text = daily.artinya

//        EXPAND
        val isExpand : Boolean = daily.isExpandable
        holder.binding.expandDesc.visibility = if (isExpand) View.VISIBLE else View.GONE
        holder.binding.viewTitle.setOnClickListener {
            daily.isExpandable = !daily.isExpandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return dataDaily.size
    }

    fun setDataDaily(newDaily: ArrayList<DailyPrayersModel>){
        dataDaily.clear()
        dataDaily.addAll(newDaily)
        notifyDataSetChanged()
    }
}