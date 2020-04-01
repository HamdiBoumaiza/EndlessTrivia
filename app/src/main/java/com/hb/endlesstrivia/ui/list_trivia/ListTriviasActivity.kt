package com.hb.endlesstrivia.ui.list_trivia

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityListTriviaBinding
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.ui.base.BaseActivity
import com.hb.endlesstrivia.utils.*


class ListTriviasActivity : BaseActivity(), View.OnClickListener {

    private fun getViewModel(): ListTriviasViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityListTriviaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
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
        binding.imgRightArrow.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    private fun setViewPagerListener(list: List<Trivia>) {
        binding.viewpagerListTrivia.addPageChangeListener { position ->
            if (position == 0) binding.imgLeftArrow.hide()
            else binding.imgLeftArrow.show()

            if (position == list.size - 1) {
                binding.imgRightArrow.hide()
            } else binding.imgRightArrow.show()

            binding.tvPositionPager.text =
                "${position + 1} / ${getViewModel().getUserPreferences()!!.amount}"
        }
    }

    private fun initObservers() {
        getViewModel().resultListTrivia.observe(this, Observer {
            initRecycler(it)
            setTriviaIcons()
        })
        getViewModel().errorMessage.observe(this, Observer {
            handleEmptyList()
            binding.constraintParent.showSnackbar(getString(it))
        })
        getViewModel().showLoading.observe(this, Observer { showLoading ->
            if (showLoading) binding.animationView.show()
            else binding.animationView.hide()
        })
    }

    private fun setTriviaIcons() {
        getViewModel().getUserPreferences()?.let {
            if (it.type == TRIVIA_TYPE_MULTIPLE) {
                binding.imgTypeTrivia.setImageDrawable(getDrawable(R.drawable.mcq))
            } else {
                binding.imgTypeTrivia.setImageDrawable(getDrawable(R.drawable.true_false))
            }
        }
    }

    private fun initRecycler(list: List<Trivia>) {
        if (list.isNotEmpty()) {
            binding.viewpagerListTrivia.adapter = TriviaViewPagerAdapter(this, list)
            setViewPagerListener(list)
        } else {
            handleEmptyList()
        }
    }

    private fun handleEmptyList() {
        with(binding) {
            viewpagerListTrivia.hide()
            tvErrorMessage.show()
        }
    }

    override fun onClick(v: View?) {
        val currentPosition = binding.viewpagerListTrivia.currentItem
        when (v) {
            binding.imgRightArrow -> {
                binding.viewpagerListTrivia.currentItem = currentPosition + 1
            }
            binding.imgLeftArrow -> {
                binding.viewpagerListTrivia.currentItem = currentPosition - 1
            }
        }
    }

}
