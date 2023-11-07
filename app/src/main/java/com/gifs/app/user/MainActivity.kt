package com.gifs.app.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gifs.app.user.ui.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gifs.app.user.databinding.ActivityMainBinding
import com.gifs.app.user.ui.adapter.ItemAdapter
import com.gifs.app.user.util.BaseState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: TestViewModel by viewModels()
    private val itemAdapter: ItemAdapter by lazy {
        ItemAdapter()
    }
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.apply {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
        setItemDataObserver()
        setSearchQuery()
    }

    private fun setSearchQuery() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    val tempQuery = query.takeUnless { it.isNullOrEmpty() }
                    val lastSearchQuery = viewModel.searchInputData.value
                    if (lastSearchQuery != tempQuery) {
                        viewModel.getData(tempQuery ?: "cats")
                    }
                    return true
                }
            })
    }

    private fun setItemDataObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemData.collectLatest {
                    when (it) {
                        is BaseState.Failure -> {
                            setErrorVisibility(true)
                            binding.tvError.text = it.errorMsg
                            binding.btnRetry.setOnClickListener {
                                setErrorVisibility(false)
                                viewModel.getData(viewModel.searchInputData.value)
                            }
                        }

                        is BaseState.Success -> {
                            setProgressVisibility(false)
                            val isEmpty = it.data.isEmpty()
                            binding.recyclerview.isVisible = !isEmpty
                            itemAdapter.submitList(it.data)
                        }

                        BaseState.Loading -> {
                            setProgressVisibility(true)
                        }
                    }
                }
            }
        }
    }

    private fun setProgressVisibility(isVisible: Boolean) {
        binding.progressbar.isVisible = isVisible
    }

    private fun setErrorVisibility(isVisible: Boolean) {
        setProgressVisibility(false)
        binding.recyclerview.isVisible = !isVisible
        binding.tvError.isVisible = isVisible
        binding.btnRetry.isVisible = isVisible
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}