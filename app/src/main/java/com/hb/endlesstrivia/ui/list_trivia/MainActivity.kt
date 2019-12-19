package com.hb.endlesstrivia.ui.list_trivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.databinding.ActivityMainBinding
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.utils.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    TriviaItemClickListener {

    override fun onTriviaItemClicked(trivia: Trivia) {
        toast(trivia.question)
    }

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): MainActivityViewModel {
        return viewModelProvider(viewModelFactory)
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
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
        getViewModel().getListOfTrivias(RequestListTrivia("50", null, "medium", "boolean"))
    }

    private fun initObservers() {
        getViewModel().resultListTrivia.observe(this, Observer {
            initRecycler(it)
        })
        getViewModel().errorMessage.observe(this, Observer {
            binding.constraintParent.showSnackbar(it)
        })
    }

    private fun initRecycler(list: List<Trivia>) {
        if (list.isNotEmpty()) {
            val triviasAdapter = TriviasAdapter(list, this)
            binding.recyclerListTrivia.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = triviasAdapter
            }
        } else {
            handleEmptyList()
        }
    }

    private fun handleEmptyList() {
        binding.recyclerListTrivia.hide()
        binding.tvErrorMessage.show()
        binding.tvErrorMessage.text = getString(R.string.no_result)
    }
}
