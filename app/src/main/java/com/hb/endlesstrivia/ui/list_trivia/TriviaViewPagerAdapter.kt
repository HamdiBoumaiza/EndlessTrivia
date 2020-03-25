package com.hb.endlesstrivia.ui.list_trivia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ItemDetailsTriviaBinding
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.utils.TRIVIA_BOOLEAN_FALSE
import com.hb.endlesstrivia.utils.TRIVIA_BOOLEAN_TRUE
import com.hb.endlesstrivia.utils.TRIVIA_TYPE_MULTIPLE
import com.hb.endlesstrivia.utils.hide


class TriviaViewPagerAdapter internal constructor(
    val context: Context,
    val data: List<Trivia>
) : RecyclerView.Adapter<TriviaViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_details_trivia,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val viewDataBinding: ItemDetailsTriviaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

        init {
            viewDataBinding.tvFirstAnswer.setOnClickListener(this)
            viewDataBinding.tvSecondAnswer.setOnClickListener(this)
            viewDataBinding.tvThirdAnswer.setOnClickListener(this)
            viewDataBinding.tvFourthAnswer.setOnClickListener(this)
        }

        private var trivias: Trivia? = null
        fun bindViewHolder(trivia: Trivia) {
            this.trivias = trivia
            viewDataBinding.item = trivia
            if (trivia.type == TRIVIA_TYPE_MULTIPLE) {
                setMultipleAnswers(trivia)
            } else {
                setBooleanAnswers()
            }
        }

        private fun setBooleanAnswers() {
            with(viewDataBinding) {
                tvFirstAnswer.text = TRIVIA_BOOLEAN_TRUE
                tvSecondAnswer.text = TRIVIA_BOOLEAN_FALSE
                tvThirdAnswer.hide()
                tvFourthAnswer.hide()
            }
        }


        private fun setMultipleAnswers(trivia: Trivia) {
            val listAllAnswers = ArrayList<String>()
            listAllAnswers.addAll(trivia.incorrect_answers)
            listAllAnswers.add(trivia.correct_answer)
            listAllAnswers.shuffle()
            with(viewDataBinding) {
                trivia.apply {
                    tvFirstAnswer.text = listAllAnswers[0]
                    tvSecondAnswer.text = listAllAnswers[1]
                    tvThirdAnswer.text = listAllAnswers[2]
                    tvFourthAnswer.text = listAllAnswers[3]
                }
            }
        }

        override fun onClick(view: View?) {
            when (view) {
                viewDataBinding.tvFirstAnswer -> {
                    verifyAnswer(viewDataBinding.tvFirstAnswer)
                }
                viewDataBinding.tvSecondAnswer -> {
                    verifyAnswer(viewDataBinding.tvSecondAnswer)
                }
                viewDataBinding.tvThirdAnswer -> {
                    verifyAnswer(viewDataBinding.tvThirdAnswer)
                }
                viewDataBinding.tvFourthAnswer -> {
                    verifyAnswer(viewDataBinding.tvFourthAnswer)
                }
            }
        }

        private fun verifyAnswer(answer: TextView) {
            if (trivias?.correct_answer == answer.text.toString()) {
                answer.background =
                    ContextCompat.getDrawable(context, R.drawable.background_right_answer)
            } else {
                answer.background =
                    ContextCompat.getDrawable(context, R.drawable.background_wrong_answer)
            }
        }

    }

}