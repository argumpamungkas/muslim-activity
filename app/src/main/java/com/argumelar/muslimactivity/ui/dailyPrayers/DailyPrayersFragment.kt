package com.argumelar.muslimactivity.ui.dailyPrayers

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.argumelar.muslimactivity.R
import com.argumelar.muslimactivity.databinding.CustomToolbarBinding
import com.argumelar.muslimactivity.databinding.FragmentDailyPrayersBinding
import com.argumelar.muslimactivity.model.DailyPrayersModel
import com.argumelar.muslimactivity.ui.adapter.AdapterListDaily
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val dailyModule = module {
    factory { DailyPrayersFragment() }
}

class DailyPrayersFragment : Fragment() {

    private lateinit var binding: FragmentDailyPrayersBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: DailyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDailyPrayersBinding.inflate(layoutInflater, container, false)
        bindingToolbar = binding.containerToolbar
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.tvTitle.text = "Doa Doa"

        binding.rvListDaily.adapter = dailyAdapter
        viewModel.daily.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                TransitionManager.beginDelayedTransition(binding.loading, AutoTransition())
                binding.loading.visibility = View.GONE
            }
            dailyAdapter.setDataDaily(it as ArrayList<DailyPrayersModel>)
        })
    }

    private val dailyAdapter by lazy {
        AdapterListDaily(arrayListOf())
    }

}