package com.devblock.login


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devblock.BaseFragmentBinding
import com.devblock.login.databinding.FragmentLoginBinding

import com.devblock.login.model.LoginState
import com.devblock.utils.DialogUtils


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class LoginFragment : BaseFragmentBinding<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()


    override fun onViewInitialized(binding: FragmentLoginBinding) {
        super.onViewInitialized(binding)
        binding.viewmodel = viewModel
        observeViewModel()
    }

    private fun observeViewModel() {


            viewModel.state.observe(this){
                when(it) {
                    is LoginState.Loading -> {
                        showProgress()

                    }
                    is LoginState.Fail -> {
                        DialogUtils.showOkAlert(requireContext(),it.errorMessage)
                    }
                    is LoginState.Success -> {
                        hideProgress()
                    }
                    is LoginState.Idle -> {}
                }
            }
        }
    }


