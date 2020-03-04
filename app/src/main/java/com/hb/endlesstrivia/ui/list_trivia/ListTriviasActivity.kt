package com.hb.endlesstrivia.ui.list_trivia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityListTriviaBinding
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.ui.details.DetailsTriviaActivity
import com.hb.endlesstrivia.utils.*
import javax.inject.Inject

class ListTriviasActivity : AppCompatActivity(),
    TriviaItemClickListener {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): ListTriviasViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityListTriviaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityListTriviaBinding>(
            this, R.layout.activity_list_trivia
        ).apply {
            lifecycleOwner = this@ListTriviasActivity
        }

        initViews()
        initObservers()
    }

    private fun initViews() {
        getViewModel().getUserPreferences()?.let {
            getViewModel().getListOfTrivias(it)
        }
    }

    private fun initObservers() {
        getViewModel().resultListTrivia.observe(this, Observer {
            initRecycler(it)
        })
        getViewModel().errorMessage.observe(this, Observer {
            handleEmptyList()
            binding.constraintParent.showSnackbar(it)
        })
        getViewModel().showLoading.observe(this, Observer { showLoading ->
            if (showLoading) binding.animationView.show()
            else binding.animationView.hide()
        })
    }

    private fun initRecycler(list: List<Trivia>) {
        if (list.isNotEmpty()) {
            val triviasAdapter = TriviasAdapter(list, this)
            binding.recyclerListTrivia.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@ListTriviasActivity)
                adapter = triviasAdapter
            }
        } else {
            handleEmptyList()
        }
    }

    private fun handleEmptyList() {
        with(binding) {
            recyclerListTrivia.hide()
            tvErrorMessage.show()
            tvErrorMessage.text = getString(R.string.no_result)
        }
    }


    override fun onTriviaItemClicked(trivia: Trivia) {
        Intent(this, DetailsTriviaActivity::class.java).apply {
            putExtra(TRIVIA_EXTRA, trivia)
            startActivity(this)
        }
    }

}
