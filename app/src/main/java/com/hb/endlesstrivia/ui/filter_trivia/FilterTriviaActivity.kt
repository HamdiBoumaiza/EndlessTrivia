package com.hb.endlesstrivia.ui.filter_trivia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hb.endlesstrivia.MainApplication
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.databinding.ActivityFilterTriviaBinding
import com.hb.endlesstrivia.ui.base.BaseActivity
import com.hb.endlesstrivia.ui.list_trivia.ListTriviasActivity
import com.hb.endlesstrivia.utils.toast
import com.hb.endlesstrivia.utils.viewModelProvider
import javax.inject.Inject


class FilterTriviaActivity : BaseActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    private lateinit var selectedCategory: String
    private lateinit var selectedDifficulty: String
    private lateinit var selectedTriviaNumber: String
    private lateinit var selectedType: String

    private val viewModel: FilterViewModel by lazy {
        viewModelProvider(viewModelFactory) as FilterViewModel
    }

    private lateinit var binding: ActivityFilterTriviaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_filter_trivia
        )

        initViews()
    }


    private fun initViews() {
        setCategorySpinner()
        setTypeSpinner()
        setSpinnerData(viewModel.getListDifficulties(), binding.spinnerDifficulty)
        setSpinnerData(viewModel.getListTriviaNumber(), binding.spinnerTriviaNumber)
        binding.buttonConfirm.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonConfirm -> {
                if (validateInput()) {
                    val request =
                        RequestListTrivia(
                            selectedTriviaNumber,
                            selectedCategory,
                            selectedDifficulty,
                            selectedType
                        )
                    viewModel.saveUserPreferences(request)
                    startActivity(Intent(this, ListTriviasActivity::class.java))
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        return when {
            !::selectedCategory.isInitialized -> {
                binding.spinnerCategory.background =
                    ContextCompat.getDrawable(this, R.drawable.background_unselected_spinner)
                toast(getString(R.string.error_select_category_message))
                false
            }
            !::selectedDifficulty.isInitialized -> {
                binding.spinnerCategory.background =
                    ContextCompat.getDrawable(this, R.drawable.background_unselected_spinner)
                toast(getString(R.string.error_select_difficulty_message))
                false
            }
            !::selectedType.isInitialized -> {
                binding.spinnerCategory.background =
                    ContextCompat.getDrawable(this, R.drawable.background_unselected_spinner)
                toast(getString(R.string.error_select_type_message))
                false
            }
            !::selectedTriviaNumber.isInitialized -> {
                binding.spinnerCategory.background =
                    ContextCompat.getDrawable(this, R.drawable.background_unselected_spinner)
                toast(getString(R.string.error_select_number_message))
                false
            }
            else -> true
        }
    }

    private fun setCategorySpinner() {
        val listCategoriesNames = ArrayList<String>()
        viewModel.getListCategories().forEach {
            listCategoriesNames.add(it.first)
        }
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listCategoriesNames
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = dataAdapter
        binding.spinnerCategory.onItemSelectedListener = this
    }

    private fun setTypeSpinner() {
        val listTypesNames = ArrayList<String>()
        resources.getStringArray(R.array.triviaNumber)
        viewModel.getListTypes().forEach {
            listTypesNames.add(it.first)
        }
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listTypesNames
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = dataAdapter
        binding.spinnerType.onItemSelectedListener = this

    }

    private fun setSpinnerData(data: List<String>, spinner: Spinner) {
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, data
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        with(spinner) {
            adapter = dataAdapter
            onItemSelectedListener = this@FilterTriviaActivity
        }
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        when (p0) {
            binding.spinnerCategory -> {
                selectedCategory = viewModel.getListCategories()[position].second
            }
            binding.spinnerType -> {
                selectedType = viewModel.getListTypes()[position].second
            }
            binding.spinnerDifficulty -> {
                selectedDifficulty = viewModel.getListDifficulties()[position]
            }
            binding.spinnerTriviaNumber -> {
                selectedTriviaNumber = viewModel.getListTriviaNumber()[position]
            }

        }

    }


}
