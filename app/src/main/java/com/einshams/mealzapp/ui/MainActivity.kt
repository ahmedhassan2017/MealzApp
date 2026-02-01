package com.einshams.mealzapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.einshams.mealzapp.R
import com.einshams.mealzapp.ui.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MealsViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorContainer: LinearLayout
    private lateinit var errorText: TextView
    private lateinit var retryButton: Button

    private val adapter by lazy {
        CategoryAdapter { category ->
            // TODO: navigate or open details
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // bind views
        recyclerView = findViewById(R.id.mealsRecyclerView)
        progressBar = findViewById(R.id.progressBar)
        errorContainer = findViewById(R.id.errorContainer)
        errorText = findViewById(R.id.errorText)
        retryButton = findViewById(R.id.retryButton)

        recyclerView.adapter = adapter

        retryButton.setOnClickListener {
            viewModel.fetchMeals()
        }

        // collect state
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                // Loading
                progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

                // Error
                val hasError = !state.errorMessage.isNullOrBlank()
                errorContainer.visibility = if (hasError) View.VISIBLE else View.GONE
                if (hasError) errorText.text = state.errorMessage

                // List
                val showList = !state.isLoading && !hasError
                recyclerView.visibility = if (showList) View.VISIBLE else View.GONE

                adapter.submitList(state.data)
            }
        }

        // initial load
        viewModel.fetchMeals()
    }
}
