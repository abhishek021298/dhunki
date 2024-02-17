package com.example.technomusic.mvvm.view.activity

import android.content.Intent
import android.view.Menu
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.example.technomusic.R
import com.example.technomusic.databinding.ActivityMainBinding
import com.example.technomusic.mvvm.base.BaseActivity
import com.example.technomusic.mvvm.view.adapter.ViewPagerAdapter
import com.example.technomusic.mvvm.viewmodel.MainViewModel
import com.example.technomusic.utils.PermissionUtils
import com.example.technomusic.utils.Utility.transparentStatusAndNavigation
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mPermission = PermissionUtils(this)
    private val viewModel: MainViewModel by viewModels()


    override fun getLayoutId(): Int = R.layout.activity_main

    override fun observeViewModel() {

    }

    override fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        manageViewPager()
        handleSystemUiIntersections()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun manageViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.offscreenPageLimit = 5
        TabLayoutMediator(binding.tabView, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.playlists)
                1 -> tab.text = getString(R.string.songs)
                2 -> tab.text = getString(R.string.artist)
                3 -> tab.text = getString(R.string.album)
                4 -> tab.text = getString(R.string.genres)
            }
        }.attach()
    }

    private fun handleSystemUiIntersections() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        ViewCompat.setOnApplyWindowInsetsListener(binding.clMain) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val navInsets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())

            binding.appBar.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }

            windowInsets
        }
        transparentStatusAndNavigation()
    }

    override fun onResume() {
        super.onResume()
        if (!mPermission.isPermissionAccepted()) {
            startActivity(Intent(this, PermissionActivity::class.java))
            finish()
        }
    }
}