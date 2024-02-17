package com.example.technomusic.mvvm.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.technomusic.R
import com.example.technomusic.databinding.ActivityPermissionBinding
import com.example.technomusic.mvvm.base.BaseActivity
import com.example.technomusic.utils.Constants
import com.example.technomusic.utils.PermissionUtils


class PermissionActivity : BaseActivity<ActivityPermissionBinding>(), View.OnClickListener {
    private val mCheckPermission = PermissionUtils(this)

    override fun getLayoutId(): Int = R.layout.activity_permission

    override fun observeViewModel() {}

    override fun initViews() {
        binding.btGivePermission.setOnClickListener(this)
        binding.btGoToSettings.setOnClickListener(this)
        checkPermission()
        binding.toolbar.transitionName = "toolbar_transition"
        binding.tvPermissionMessage.transitionName = "title_transition"


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btGivePermission -> {
                mCheckPermission.requestReadStoragePermission()
            }

            R.id.btGoToSettings -> {
                goToSettings()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.IntentKey.MY_PERMISSIONS_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openMainActivity()
            } else {
                binding.tvPermissionMessage.text = getString(R.string.permission_message_rationale)
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_AUDIO)) {
                    binding.tvPermissionMessage.text = getString(R.string.permission_message_manual)
                    binding.btGoToSettings.visibility = View.VISIBLE
                    binding.btGivePermission.visibility = View.GONE
                }
            }
        }
    }

    private fun checkPermission() {
        if (mCheckPermission.isPermissionAccepted()) {
            openMainActivity()
        }

    }

    private fun goToSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.data = uri
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        if (mCheckPermission.isPermissionAccepted()) {
            openMainActivity()
        }
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRestart() {
        Log.e("TAG", "onRestart: ")
        super.onRestart()
        Log.e("TAG", "onRestart: ")
    }
}