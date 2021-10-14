package com.devblock.splash


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devblock.BaseFragment
import com.devblock.splash.model.SplashState


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
internal class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val viewModel: SplashViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope?.launchWhenCreated {
            viewModel.state.collect {
                when(it){
                    is SplashState.Idle -> {
                        hideProgress()
                    }
                    is SplashState.Loading -> {
                        showProgress()
                    }

            }
        } }
    }


}