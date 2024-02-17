package com.example.technomusic.mvvm.view.fragment

import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.technomusic.R
import com.example.technomusic.data.model.Genres
import com.example.technomusic.databinding.FragmentGenresBinding
import com.example.technomusic.mvvm.base.BaseFragment
import com.example.technomusic.mvvm.view.adapter.GenresAdapter
import com.example.technomusic.mvvm.viewmodel.GenresViewModel
import com.example.technomusic.mvvm.viewmodel.MainViewModel
import com.example.technomusic.utils.Resource
import com.example.technomusic.utils.Utility.transparentStatusAndNavigation
import com.example.technomusic.utils.px
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : BaseFragment<FragmentGenresBinding>() {
    private val viewModel: GenresViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: GenresAdapter

    override fun getLayoutId(): Int = R.layout.fragment_genres

    override fun observeViewModel() {
        activityViewModel.genresLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }

                is Resource.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    setupAdapter(it.data)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Can not find any media file", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun init() {
        handleSystemUiIntersections()
        binding.swipeRefresh.isRefreshing = true
        manageRefresh()
    }


    private fun handleSystemUiIntersections() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

        ViewCompat.setOnApplyWindowInsetsListener(binding.clMain) { _, windowInsets ->
            val navInsets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())

            binding.recyclerView.updatePadding(0, 0, 0, navInsets.bottom + 20.px)

            WindowInsetsCompat.CONSUMED
        }
        requireActivity().transparentStatusAndNavigation()
    }

    private fun manageRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupAdapter(data: List<Genres>) {
        adapter = GenresAdapter(data, requireContext())
        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.isRefreshing = false
    }
}