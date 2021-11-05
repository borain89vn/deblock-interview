package com.devblock.welcome


import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.widget.ImageViewCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.devblock.adapter.BasePagingAdapter
import com.devblock.adapter.PagingLoadStateAdapter
import com.devblock.base.BaseFragment
import com.devblock.adapter.SingleLayoutAdapter
import com.devblock.adapter.SingleLayoutPagingAdapter
import com.devblock.extension.observe
import com.devblock.network.api.response.ContactItemResp
import com.devblock.utils.paging.ItemComparable
import com.devblock.welcome.databinding.FragmentWelcomeBinding
import com.devblock.welcome.databinding.ItemContactBinding
import com.devblock.welcome.model.ContactModel


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
internal class WelcomeFragment : BaseFragment<FragmentWelcomeBinding,WelcomeViewModel>() {

    private val viewModel: WelcomeViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_welcome

    override fun getVM(): WelcomeViewModel  = viewModel
    var contactAdapter: WelcomePagingAdapter? =null

    override fun bindVM(binding: FragmentWelcomeBinding, vm: WelcomeViewModel) {
         contactAdapter = WelcomePagingAdapter()
        with(contactAdapter){

           binding.recyclerView.adapter = this?.withLoadStateHeaderAndFooter(
               header = PagingLoadStateAdapter(this),
               footer = PagingLoadStateAdapter(this)
           )

        }
        with(vm){
            observe(state){
                when(it){

                    is WelcomeState.Contacts ->{
                        launchOnLifecycleScope {
                            it.contacts.collect {
                                contactAdapter?.submitData(it)
                            }

                        }


                    }

                    is WelcomeState.User -> {
                        binding.title.text =
                            context?.getString(R.string.welcome_toolbar_title)?.let { it1 ->
                                String.format(
                                    it1,it.userName)
                            }
                    }
                }
            }


            observe(filterState){
                ImageViewCompat.setImageTintMode(binding.filterImage, PorterDuff.Mode.SRC_ATOP);
                if(it) {

                    ImageViewCompat.setImageTintList(binding.filterImage, ColorStateList.valueOf(requireContext().getColor(R.color.teal_200)));
                } else {
                    ImageViewCompat.setImageTintList(binding.filterImage, ColorStateList.valueOf(requireContext().getColor(R.color.gray_808080)));

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveUpdateEvent()
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