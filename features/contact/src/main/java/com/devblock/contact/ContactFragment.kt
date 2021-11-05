package com.devblock.contact



import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.devblock.base.BaseFragment
import com.devblock.contact.databinding.FragmentContactBinding
import com.devblock.extension.observe
import com.devblock.utils.DialogUtils


import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class ContactFragment : BaseFragment<FragmentContactBinding, ContactViewModel>() {

    @Inject
    lateinit var factory: ContactViewModelAssistedFactory

    private val args: ContactFragmentArgs by navArgs()


    private val viewModel: ContactViewModel by viewModels {
        factory.create(args.id,args.name, args.email,args.avatar,this)
    }


    override val layoutId: Int = R.layout.fragment_contact


    override fun getVM(): ContactViewModel  = viewModel

    override fun bindVM(binding: FragmentContactBinding, vm: ContactViewModel) {
        with(binding){
            binding.viewModel = vm
            with(vm){
                observe(state){
                    when(it){
                        is ContactState.UpdateSuccess -> {
                            DialogUtils.showOkAlert(requireContext(),it.message){
                                sendUpdateEventToWelcome()
                                backClick()
                            }
                        }
                        is ContactState.UpdateFail -> {
                            DialogUtils.showOkAlert(requireContext(),it.message)
                        }
                        is ContactState.Contact -> {
                            contact = it
                        }
                    }
                }
            }
        }
    }





    private fun sendUpdateEventToWelcome() {
        parentFragment?.setFragmentResult(KEY_REQUEST_UPDATE , bundleOf((KEY_REQUEST_UPDATE  to args.id)))
    }

    companion object {

        private const val KEY_REQUEST_UPDATE = "UPDATE"

    }



}