package com.argumelar.muslimactivity.ui.quran.quran

import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.argumelar.muslimactivity.ui.quran.detail.DetailQuran
import com.argumelar.muslimactivity.ui.adapter.AdapterListSurah
import com.argumelar.muslimactivity.databinding.CustomToolbarBinding
import com.argumelar.muslimactivity.databinding.FragmentQuranBinding
import com.argumelar.muslimactivity.model.QuranModel
import com.argumelar.muslimactivity.source.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val quranModule = module {
    factory { QuranFragment() }
}

class QuranFragment : Fragment() {

    private lateinit var binding: FragmentQuranBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: QuranViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuranBinding.inflate(layoutInflater, container, false)
        bindingToolbar = binding.containerToolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvListSurah.adapter = adapterListSurah
        viewModel.quran.observe(viewLifecycleOwner, Observer {
            if (it.data!!.isNotEmpty()){
                TransitionManager.beginDelayedTransition(binding.loading, AutoTransition())
                binding.loading.visibility = View.GONE
            }
            adapterListSurah.setData(it.data as ArrayList<QuranModel>)

        })
    }

    private val adapterListSurah by lazy {
        AdapterListSurah(arrayListOf(), object : AdapterListSurah.OnAdapterListener{
            override fun onClick(qurans: QuranModel) {
                Constant.NUMBER = qurans.number!!
                startActivity(Intent(requireContext(), DetailQuran::class.java)
                    .putExtra("recitation", qurans))
            }

        })
    }

}