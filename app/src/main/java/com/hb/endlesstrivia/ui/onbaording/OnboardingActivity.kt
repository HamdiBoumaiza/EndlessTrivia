package com.hb.endlesstrivia.ui.onbaording

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityOnboardingBinding
import com.hb.endlesstrivia.utils.viewModelProvider
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity() {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): OnboardingViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_onboarding
        )

    }
}
