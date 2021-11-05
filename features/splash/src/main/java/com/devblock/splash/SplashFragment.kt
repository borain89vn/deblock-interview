package com.devblock.splash


import androidx.fragment.app.viewModels
import com.devblock.base.BaseFragment
import com.devblock.splash.databinding.FragmentSplashBinding



import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SplashFragment : BaseFragment<FragmentSplashBinding,SplashViewModel>() {
    private val viewModel: SplashViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_splash


    override fun getVM(): SplashViewModel  = viewModel


    override fun bindVM(binding: FragmentSplashBinding, vm: SplashViewModel)  = Unit
}