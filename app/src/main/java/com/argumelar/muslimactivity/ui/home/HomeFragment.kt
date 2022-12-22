package com.argumelar.muslimactivity.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.argumelar.muslimactivity.R
import com.argumelar.muslimactivity.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import java.text.SimpleDateFormat
import java.util.*

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchJadwal()
        viewModel.fetchDetailKota()

//        DATA KOTA
        viewModel.kota.observe(viewLifecycleOwner, Observer {
            for (kota in it.kota) {
                binding.tvKota.text = kota.nama
            }
        })

//        SEMUA JADWAL
        viewModel.jadwal.observe(viewLifecycleOwner, Observer {
            if (it.jadwal!!.data!!.subuh!!.isNotEmpty()) {
                TransitionManager.beginDelayedTransition(binding.loading, AutoTransition())
                binding.loading.visibility = View.GONE
                binding.btnSetLokasi.visibility = View.VISIBLE
            }
            binding.tvShubuh.text = it.jadwal.data!!.subuh
            binding.tvDzhuhur.text = it.jadwal.data.dzuhur
            binding.tvAshar.text = it.jadwal.data.ashar
            binding.tvMaghrib.text = it.jadwal.data.maghrib
            binding.tvIsya.text = it.jadwal.data.isya

            time(
                subuh = it.jadwal.data.subuh!!,
                dzuhur = it.jadwal.data.dzuhur!!,
                ashar = it.jadwal.data.ashar!!,
                maghrib = it.jadwal.data.maghrib!!,
                isya = it.jadwal.data.isya!!
            )
        })

//        SEMUA KOTA
        viewModel.allCity.observe(viewLifecycleOwner, Observer {

            var listIdKota = ArrayList<String>()
            var listNamaKota = ArrayList<String>()

            val listReponse = it.kota
            listReponse.forEach { response ->
                listIdKota.add(response.id!!)
                listNamaKota.add(response.nama)
            }

            binding.btnSetLokasi.setOnClickListener {

                val dialogBinding = layoutInflater.inflate(R.layout.dialog_spinner, null)
                val myDialog = Dialog(requireContext())
                myDialog.setContentView(dialogBinding)

                val spinner = dialogBinding.findViewById(R.id.spinner) as Spinner
                val adapterKota = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listNamaKota
                )
                spinner.adapter = adapterKota

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (parent?.selectedItem == spinner.selectedItem) {
                            viewModel.saveData(listIdKota[position].toInt())
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

                val selesai = dialogBinding.findViewById<TextView>(R.id.btn_selesai)
                val batal = dialogBinding.findViewById<TextView>(R.id.btn_batal)
                selesai.setOnClickListener {
                    refresh()
                    myDialog.dismiss()
                }
                batal.setOnClickListener {
                    myDialog.dismiss()
                }

                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
            }

        })

    }

    //    REFRESH
    fun refresh() {
        Log.i("refresh", "ini refresh")
        val navController = findNavController()
        navController.navigate(R.id.action_navigation_home_self)
    }

    //    Penentuan Waktu
    private fun time(subuh: String, dzuhur: String, ashar: String, maghrib: String, isya: String) {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                updateTime(subuh, dzuhur, ashar, maghrib, isya)
            }
        }, 0, 1000)
    }

    //    UPDATE WAKTU
    @SuppressLint("SetTextI18n")
    fun updateTime(subuh: String, dzuhur: String, ashar: String, maghrib: String, isya: String) {
        val current = Calendar.getInstance().time
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(current)
        val day = SimpleDateFormat("EEEE, d MMMM ", Locale.getDefault()).format(current)
//        LOOPING UNTUK WAKTU YANG TERUS BERTAMBAH
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            binding.tvDay.text = day
            binding.tvTime.text = time
            when {
                time <= subuh -> {
                    binding.tvJamSolat.text = subuh
                    binding.tvWaktuSolat.text = "Subuh"
                    binding.imgHeader.setImageResource(R.drawable.img_subuh)
                }
                time <= dzuhur -> {
                    binding.tvJamSolat.text = dzuhur
                    binding.tvWaktuSolat.text = "Dzhuhur"
                    binding.imgHeader.setImageResource(R.drawable.img_dhuhur)
                }
                time <= ashar -> {
                    binding.tvJamSolat.text = ashar
                    binding.tvWaktuSolat.text = "Ashar"
                    binding.imgHeader.setImageResource(R.drawable.img_ashar)
                }
                time <= maghrib -> {
                    binding.tvJamSolat.text = maghrib
                    binding.tvWaktuSolat.text = "Maghrib"
                    binding.imgHeader.setImageResource(R.drawable.img_maghrib)
                }
                time <= isya -> {
                    binding.tvJamSolat.text = isya
                    binding.tvWaktuSolat.text = "Isya"
                    binding.imgHeader.setImageResource(R.drawable.img_isya)
                }
                else -> {
                    binding.tvJamSolat.text = subuh
                    binding.tvWaktuSolat.text = "Subuh"
                    binding.imgHeader.setImageResource(R.drawable.img_subuh)
                }

            }
        }
    }

}