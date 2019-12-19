package com.hb.endlesstrivia.ui.filter_trivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityFilterTriviaBinding
import com.hb.endlesstrivia.utils.viewModelProvider
import javax.inject.Inject

class FilterTriviaActivity : AppCompatActivity() {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FilterViewModel by lazy {
        viewModelProvider(viewModelFactory) as FilterViewModel
    }

    private lateinit var binding: ActivityFilterTriviaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_filter_trivia
        )

    }
}
