package com.argumelar.muslimactivity.ui.quran.detail

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.argumelar.muslimactivity.R
import com.argumelar.muslimactivity.databinding.ActivityDetailQuranBinding
import com.argumelar.muslimactivity.databinding.CustomDescriptionBinding
import com.argumelar.muslimactivity.databinding.CustomToolbarBinding
import com.argumelar.muslimactivity.model.QuranModel
import com.argumelar.muslimactivity.ui.adapter.AdapterDetailSurah
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailQuranModule = module {
    factory { DetailQuran() }
}

class DetailQuran : AppCompatActivity() {

    private lateinit var binding: ActivityDetailQuranBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private lateinit var bindingContent: CustomDescriptionBinding
    private val viewModel: DetailQuranViewModel by viewModel()
    private val dataSurah by lazy {
        intent.getSerializableExtra("recitation") as QuranModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailQuranBinding.inflate(layoutInflater)
        bindingToolbar = binding.containerToolbar
        bindingContent = binding.containerDescription
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        binding.rvDetailSurah.adapter = adapterDetailSurah
        viewModel.detailQuran.observe(this, Observer { surah ->
            bindingToolbar.tvTitle.text = surah.data!!.asma!!.id!!.short
            bindingContent.ivArrow.setOnClickListener { expand() }
            if (surah.data.ayahs!!.isNotEmpty()){
                TransitionManager.beginDelayedTransition(binding.loading, AutoTransition())
                binding.loading.visibility = View.GONE
                binding.btnPlay.visibility = View.VISIBLE
            }
            adapterDetailSurah.setData(surah.data.ayahs)

//               PRE BISMILLAH
            if (surah.data.preBismillah == null) {
                binding.tvPreBismillah.visibility = View.GONE
            } else {
                binding.tvArBismillah.text = surah.data.preBismillah.text!!.ar
                binding.tvReadBismillah.text = surah.data.preBismillah.text.read
                binding.tvTranslationBismillah.text = surah.data.preBismillah.translation!!.id
                binding.tvPreBismillah.visibility = View.VISIBLE
            }

//            SURAH
            bindingContent.namaSurat.text = surah.data.asma!!.id!!.short
            bindingContent.artiSurat.text = surah.data.asma.translation!!.id
            bindingContent.jumlahAyat.text = surah.data.ayahCount.toString()
            bindingContent.typeAyat.text = surah.data.type!!.id + " / " + surah.data.type.ar
            bindingContent.tafsirAyat.text = surah.data.tafsir!!.id

            viewModel.play.observe(this, Observer {
                if (it == 0) {
                    binding.btnPlay.setImageResource(R.drawable.ic_play)
                } else {
                    binding.btnPlay.setImageResource(R.drawable.ic_stop)
                }

                val originalUrl = surah.data.recitation!!.full.toString()
                val newUrl = originalUrl.replace("http", "https")
                Log.i("URL_BARU", " url baru -> $newUrl")

                binding.btnPlay.setOnClickListener {
                    viewModel.playSurah(newUrl)
                }
            })

            scrollListener()

        })

    }

    private val adapterDetailSurah by lazy {
        AdapterDetailSurah(arrayListOf())
    }

    private fun expand() {
        if (bindingContent.cardExpand.visibility == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(bindingContent.cardView, AutoTransition())
            bindingContent.cardExpand.visibility = View.GONE
            bindingContent.ivArrow.setImageResource(R.drawable.arrow_down)
        } else {
            TransitionManager.beginDelayedTransition(bindingContent.cardView, AutoTransition())
            bindingContent.cardExpand.visibility = View.VISIBLE
            bindingContent.ivArrow.setImageResource(R.drawable.arrow_up)
        }
    }

    private fun scrollListener() {
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY + 10) {
                binding.btnPlay.hide()
            }
            if (scrollY < oldScrollY - 10) {
                binding.btnPlay.show()
            }
            if (scrollY == 0) {
                binding.btnPlay.show()
            }
        })
    }
}