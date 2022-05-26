package com.example.mymaster.presentations.Stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymaster.databinding.FragmentStatBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentStat : Fragment() {
    private val vm by viewModel<StatViewModel>()
    private var _binding: FragmentStatBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val countAllServices = binding.countAllServices
        val countCompleteServices = binding.countCompleteServices
        val countProfit = binding.countProfit
        val countProfitMonth = binding.countProfitMonth
        val countDefinition = binding.countDefenitions

        vm.resultLive.observe(this, {
            countAllServices.text = it?.allServices
            countCompleteServices.text = it?.completeServices
            countProfit.text = it?.allIncome
            countProfitMonth.text = it?.incomeOfMonth
            countDefinition.text = it?.definition
        })

        vm.load()
        return root
    }

}