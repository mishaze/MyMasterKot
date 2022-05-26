package com.example.mymaster.presentations.ScheduleSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymaster.MAIN
import com.example.mymaster.R
import com.example.mymaster.databinding.FragmentMainScheduleSettingBinding
import com.example.mymaster.presentations.Settings.SettingViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentScheduleSetting() : Fragment() {
    private var _binding: FragmentMainScheduleSettingBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModel<SettingViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainScheduleSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        vm.load()
        vm.resultLive.observe(this, {
            it?.num?.let { it1 -> initial(it1) }
        })

        return root
    }

    private fun initial(num: Int) {
        binding.viewPager.adapter = PagerAdapter(MAIN, num)
        binding.tabs.tabIconTint = null
        TabLayoutMediator(binding.tabs, binding.viewPager)
        { tab, pos ->
            when (pos) {
                0 -> tab.setText(R.string.tab_text_1)
                else -> tab.setText(R.string.tab_text_2)
            }
        }.attach()
    }


}

