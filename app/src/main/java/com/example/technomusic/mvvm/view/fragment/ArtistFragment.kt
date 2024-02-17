package com.example.technomusic.mvvm.view.fragment

import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.technomusic.R
import com.example.technomusic.data.model.Artist
import com.example.technomusic.databinding.FragmentArtistBinding
import com.example.technomusic.mvvm.base.BaseFragment
import com.example.technomusic.mvvm.view.adapter.ArtistAdapter
import com.example.technomusic.mvvm.viewmodel.ArtistViewModel
import com.example.technomusic.mvvm.viewmodel.MainViewModel
import com.example.technomusic.utils.Resource
import com.example.technomusic.utils.Utility.transparentStatusAndNavigation
import com.example.technomusic.utils.px
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment : BaseFragment<FragmentArtistBinding>() {
    private val viewModel: ArtistViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private var adapter: ArtistAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_artist

    override fun observeViewModel() {
        activityViewModel.artistLiveData.observe(viewLifecycleOwner) {
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

                else -> {

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

    private fun setupAdapter(data: List<Artist>) {
        adapter = context?.let { ArtistAdapter(data, it) }
        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.isRefreshing = false
    }
}