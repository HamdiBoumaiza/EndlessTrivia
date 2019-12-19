package com.hb.endlesstrivia.ui.list_trivia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.databinding.ItemTriviaBinding
import com.hb.endlesstrivia.model.Trivia

class TriviasAdapter(
    val listTrivias: List<Trivia>,
    val triviaItemClickListener: TriviaItemClickListener
) :
    RecyclerView.Adapter<TriviasAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return listTrivias.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_trivia,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(listTrivias[position])
    }

    inner class ViewHolder(private val viewDataBinding: ItemTriviaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bindViewHolder(trivia: Trivia) {
            viewDataBinding.item = trivia
            viewDataBinding.itemClickListener = triviaItemClickListener
        }
    }
}