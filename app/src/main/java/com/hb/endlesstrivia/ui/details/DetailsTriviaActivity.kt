package com.hb.endlesstrivia.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityDetailsTriviaBinding
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.utils.TRIVIA_EXTRA
import com.hb.endlesstrivia.utils.viewModelProvider
import javax.inject.Inject

class DetailsTriviaActivity : AppCompatActivity() {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): DetailsViewModel {
        return viewModelProvider(viewModelFactory)
    }


    private lateinit var binding: ActivityDetailsTriviaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_details_trivia
        )

        intent?.let {
            val trivia = it.getSerializableExtra(TRIVIA_EXTRA) as Trivia
        }
    }
}
