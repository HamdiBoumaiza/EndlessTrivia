package com.hb.endlesstrivia.ui.onbaording

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityOnboardingBinding
import com.hb.endlesstrivia.ui.base.BaseActivity
import com.hb.endlesstrivia.ui.filter_trivia.FilterTriviaActivity
import com.hb.endlesstrivia.utils.viewModelProvider


class OnboardingActivity : BaseActivity() {

    private fun getViewModel(): OnboardingViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_onboarding
        )


        binding.animationView.addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@OnboardingActivity, FilterTriviaActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })

    }
}
