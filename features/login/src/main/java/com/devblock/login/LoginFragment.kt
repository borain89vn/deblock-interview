package com.devblock.login


import androidx.fragment.app.viewModels
import com.devblock.base.BaseFragment
import com.devblock.extension.observe
import com.devblock.login.databinding.FragmentLoginBinding

import com.devblock.utils.DialogUtils


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private val viewModel: LoginViewModel by viewModels()


    override val layoutId: Int = R.layout.fragment_login


    override fun getVM(): LoginViewModel = viewModel
    override fun bindVM(binding: FragmentLoginBinding, vm: LoginViewModel) {
        with(binding){
            viewmodel = vm
        }
    }
}

