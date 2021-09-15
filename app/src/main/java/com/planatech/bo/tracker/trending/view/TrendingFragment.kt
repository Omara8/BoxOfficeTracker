package com.planatech.bo.tracker.trending.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.planatech.bo.tracker.comingsoon.view.ComingSoonAdapter
import com.planatech.bo.tracker.databinding.FragmentComingSoonBinding
import com.planatech.bo.tracker.trending.viewmodel.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//REMEMBER TO UPDATE THE FRAGMENT TO USE IT'S OWN LAYOUT AND ADAPTERS
@AndroidEntryPoint
class TrendingFragment: Fragment() {
    private val trendingViewModel: TrendingViewModel by viewModels()
    private var binding: FragmentComingSoonBinding? = null
    private var comingSoonAdapter: ComingSoonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComingSoonBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.mainContent?.showLoading()
        initAdapter()
        getTrending()
    }

    private fun initAdapter() {
        comingSoonAdapter = ComingSoonAdapter {
            //item clicked and should show details
        }
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding?.layoutManager = linearLayoutManager
        binding?.comingSoonAdapter = comingSoonAdapter
    }

    private fun getTrending() {
        viewLifecycleOwner.lifecycleScope.launch {
            trendingViewModel.getTrending().collectLatest {
                binding?.mainContent?.showContent()
                comingSoonAdapter?.submitData(it)
            }
        }
    }

}