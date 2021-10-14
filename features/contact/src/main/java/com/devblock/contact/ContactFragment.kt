package com.devblock.contact



import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.devblock.BaseFragmentBinding
import com.devblock.contact.ContactViewModel
import com.devblock.contact.R
import com.devblock.contact.databinding.FragmentContactBinding
import com.devblock.contact.model.ContactState
import com.devblock.db.api.models.Contact
import com.devblock.network.api.response.ContactItemResp
import com.devblock.network.api.response.ContactResp
import com.devblock.utils.DialogUtils


import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class ContactFragment : BaseFragmentBinding<FragmentContactBinding>(R.layout.fragment_contact) {

    @Inject
    lateinit var factory: ContactViewModelAssistedFactory

    private val args: ContactFragmentArgs by navArgs()


    private val viewModel: ContactViewModel by viewModels {
        factory.create(args.id,args.name, args.email,args.avatar,this)
    }




    override fun onViewInitialized(binding: FragmentContactBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        viewModel.onViewInitialized()
        viewModel.state.observe(this){
            when(it){
                is ContactState.UpdateSuccess -> {
                    DialogUtils.showOkAlert(requireContext(),it.message){
                        sendUpdateEventToWelcome()
                        viewModel.backClick()
                    }
                }
                is ContactState.UpdateFail -> {
                    DialogUtils.showOkAlert(requireContext(),it.message)
                }
                is ContactState.Contact -> {
                    binding.contact = it
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