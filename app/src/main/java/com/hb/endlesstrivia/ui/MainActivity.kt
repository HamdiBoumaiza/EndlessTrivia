package com.hb.endlesstrivia.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.databinding.ActivityMainBinding
import com.hb.endlesstrivia.di.viewmodels.DaggerViewModelFactory
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.ui.base.BaseViewModelActivity
import com.hb.endlesstrivia.utils.toast
import com.hb.endlesstrivia.utils.viewModelProvider
import javax.inject.Inject

class MainActivity : BaseViewModelActivity<MainActivityViewModel>(), TriviaItemClickListener {

    override fun onTriviaItemClicked(trivia: Trivia) {
        toast(trivia.question)
    }

    private val component by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    override fun getViewModel(): MainActivityViewModel? {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            mainViewModel = getViewModel()
            lifecycleOwner = this@MainActivity
        }

        initViews()
        initObservers()
    }

    private fun initViews() {
        getViewModel()?.getListOfTrivias(RequestListTrivia("10", "9", "medium", "boolean"))
    }

    private fun initObservers() {
        getViewModel()?.resultListTrivia?.observe(this, Observer {
            initRecycler(it)
        })
        getViewModel()?.errorMessage?.observe(this, Observer {
            toast(it)
        })
    }

    private fun initRecycler(list: List<Trivia>) {
        val triviasAdapter = TriviasAdapter(list, this)
        binding.recyclerListTrivia.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = triviasAdapter
        }
    }
}
