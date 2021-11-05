package com.devblock.interview

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import com.devblock.base.BaseActivity
import com.devblock.interview.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : BaseActivity<MainActivityBinding,MainViewModel> (){

    private val viewModel: NavigationViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.main_activity

    override fun getVM(): MainViewModel  = MainViewModel()

    override fun bindVM(binding: MainActivityBinding, vm: MainViewModel) = Unit



    override fun onStart() {
        super.onStart()
        viewModel.navController = findNavController(R.id.fragmentContainerView)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.navController = null
    }


}