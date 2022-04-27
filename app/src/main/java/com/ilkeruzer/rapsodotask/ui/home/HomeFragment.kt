package com.ilkeruzer.rapsodotask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity
import com.ilkeruzer.rapsodotask.databinding.FragmentHomeBinding
import com.ilkeruzer.rapsodotask.databinding.ItemMotionLayoutBinding
import com.ilkeruzer.rapsodotask.ui.adapter.MotionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), MotionAdapter.MotionClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val mViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var motionAdapter: MotionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            navigateMotionFragment(true)
        }

        initPagingAdapter()
    }

    private fun initPagingAdapter() {
        motionAdapter.motionClickListener = this@HomeFragment
        binding.motionRecyclerView.adapter = motionAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            mViewModel.getMotions().collectLatest {
                motionAdapter.submitData(it)
            }
        }
    }

    private fun navigateMotionFragment(isNew: Boolean, motion: MotionEntity? = null) {
        val action = HomeFragmentDirections.actionHomeFragmentToMotionFragment(isNew)
        findNavController().navigate(action)
    }

    override fun onMotionClicked(binding: ItemMotionLayoutBinding, motion: MotionEntity) {
        navigateMotionFragment(false, motion)
    }

}