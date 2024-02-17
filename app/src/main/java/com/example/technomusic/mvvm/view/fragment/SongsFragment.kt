package com.example.technomusic.mvvm.view.fragment

import android.content.Intent
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.technomusic.R
import com.example.technomusic.data.model.Song
import com.example.technomusic.databinding.FragmentSongsBinding
import com.example.technomusic.mvvm.base.BaseFragment
import com.example.technomusic.mvvm.view.activity.PlayerActivity
import com.example.technomusic.mvvm.view.adapter.SongsListAdapter
import com.example.technomusic.mvvm.viewmodel.MainViewModel
import com.example.technomusic.mvvm.viewmodel.SongsViewModel
import com.example.technomusic.utils.Resource
import com.example.technomusic.utils.Utility.transparentStatusAndNavigation
import com.example.technomusic.utils.px
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongsFragment : BaseFragment<FragmentSongsBinding>() {
    private val viewModel: SongsViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private var adapter: SongsListAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_songs

    override fun observeViewModel() {
        activityViewModel.songsLiveData.observe(viewLifecycleOwner) {
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

    private fun setupAdapter(data: List<Song>) {
        adapter = SongsListAdapter(data) {
            handleMediaClick(it)
        }

        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.isRefreshing = false
    }

    private fun handleMediaClick(it: Song) {
        val intent = Intent(requireContext(), PlayerActivity::class.java)
        intent.putExtra("data", it)
        startActivity(intent)
    }

    private fun manageRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }
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
}