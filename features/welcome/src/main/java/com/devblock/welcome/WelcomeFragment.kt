package com.devblock.welcome


import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devblock.BaseFragmentBinding
import com.devblock.adapter.SingleLayoutAdapter
import com.devblock.network.api.response.ContactItemResp
import com.devblock.utils.DialogUtils
import com.devblock.welcome.databinding.FragmentWelcomeBinding
import com.devblock.welcome.databinding.ItemContactBinding
import com.devblock.welcome.model.WelcomeState


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class WelcomeFragment : BaseFragmentBinding<FragmentWelcomeBinding>(R.layout.fragment_welcome) {

    private val viewModel: WelcomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
        receiveUpdateEvent()
    }


    override fun onViewInitialized(binding: FragmentWelcomeBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        binding.adapter = SingleLayoutAdapter<ContactItemResp, ItemContactBinding>(
            R.layout.item_contact,
            emptyList(),
            viewModel
        )
        viewModel.state.observe(this, Observer {

            when(it){
                is WelcomeState.Loading -> {
                    showProgress()
                }
                is WelcomeState.Contacts ->{
                    hideProgress()
                    binding.adapter?.swapItems(it.contacts!!)

                }
                is WelcomeState.Error -> {
                    hideProgress()
                    DialogUtils.showOkAlert(requireContext(),it.error)

                }
                is WelcomeState.User -> {
                    binding.title.text =
                        context?.getString(R.string.welcome_toolbar_title)?.let { it1 ->
                            String.format(
                                it1,it.userName)
                        }
                }
            }
        })
        viewModel.filterState.observe(this){
            ImageViewCompat.setImageTintMode(binding.filterImage, PorterDuff.Mode.SRC_ATOP);
            if(it) {

                ImageViewCompat.setImageTintList(binding.filterImage, ColorStateList.valueOf(requireContext().getColor(R.color.teal_200)));
            } else {
                ImageViewCompat.setImageTintList(binding.filterImage, ColorStateList.valueOf(requireContext().getColor(R.color.gray_808080)));

            }
        }




    }

    private fun receiveUpdateEvent() {
        parentFragment?.setFragmentResultListener(KEY_REQUEST_UPDATE) { _, result ->
            result.getString(KEY_REQUEST_UPDATE)?.let {
                viewModel.refreshData(it)
            }
        }
    }

    companion object {

        private const val KEY_REQUEST_UPDATE = "UPDATE"

    }


}