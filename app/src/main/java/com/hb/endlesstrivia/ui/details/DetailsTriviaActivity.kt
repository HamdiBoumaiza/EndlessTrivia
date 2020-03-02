package com.hb.endlesstrivia.ui.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ActivityDetailsTriviaBinding
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.utils.*
import javax.inject.Inject

class DetailsTriviaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var trivia: Trivia

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
            trivia = it.getSerializableExtra(TRIVIA_EXTRA) as Trivia
            setClickListeners()
            with(binding) {
                tvQuestion.text = trivia.question
                if (trivia.type == TRIVIA_TYPE_MULTIPLE) {
                    setMultipleAnswers(trivia)
                } else {
                    setBooleanAnswers()
                }
            }
        }
    }

    private fun setClickListeners() {
        if (::trivia.isInitialized) {
            binding.tvFirstAnswer.setOnClickListener(this)
            binding.tvSecondAnswer.setOnClickListener(this)
            binding.tvThirdAnswer.setOnClickListener(this)
            binding.tvFourthAnswer.setOnClickListener(this)
        }
    }

    private fun ActivityDetailsTriviaBinding.setBooleanAnswers() {
        tvFirstAnswer.text = TRIVIA_BOOLEAN_TRUE
        tvSecondAnswer.text = TRIVIA_BOOLEAN_FALSE
        tvThirdAnswer.hide()
        tvFourthAnswer.hide()
    }

    private fun ActivityDetailsTriviaBinding.setMultipleAnswers(trivia: Trivia) {
        val listAllAnswers = ArrayList<String>()
        listAllAnswers.addAll(trivia.incorrect_answers)
        listAllAnswers.add(trivia.correct_answer)
        listAllAnswers.shuffle()
        tvFirstAnswer.text = listAllAnswers[0]
        tvSecondAnswer.text = listAllAnswers[1]
        tvThirdAnswer.text = listAllAnswers[2]
        tvFourthAnswer.text = listAllAnswers[3]
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.tvFirstAnswer -> {
                verifyAnswer(binding.tvFirstAnswer)
            }
            binding.tvSecondAnswer -> {
                verifyAnswer(binding.tvSecondAnswer)
            }
            binding.tvThirdAnswer -> {
                verifyAnswer(binding.tvThirdAnswer)
            }
            binding.tvFourthAnswer -> {
                verifyAnswer(binding.tvFourthAnswer)
            }
        }
    }

    private fun verifyAnswer(answer: TextView) {
        if (trivia.correct_answer == answer.text.toString()) {
            answer.background = ContextCompat.getDrawable(this,R.drawable.background_right_answer)
        } else {
            answer.background = ContextCompat.getDrawable(this,R.drawable.background_wrong_answer)
        }
    }
}