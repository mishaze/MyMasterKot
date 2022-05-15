package com.example.mymaster.presentations.MyProfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.domain.Domain.models.UserInformation
import com.example.mymaster.MAIN
import com.example.mymaster.Main3Activity
import com.example.mymaster.R
import com.example.mymaster.databinding.FragmentMyProfileBinding
import com.example.mymaster.presentations.servicesListActivity.FragmentServicesList
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentMyProfile : Fragment() {
    private val vm by viewModel<MyProfileViewModel>()
    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val firstName = binding.mpFirstname
        val secondName = binding.mpName
        val phone = binding.mpPhone
        val email = binding.mpEmail
        email.isEnabled = false

        val address = binding.mpAddress
        val info = binding.mpInfo
        val services = binding.mpBtnServices
        val save = binding.mpBtnSave

        vm.resultLive.observe(this, {
            firstName.setText(it?.name)
            secondName.setText(it?.surname)
            phone.setText(it?.phone_number)
            email.setText(it?.email)
            info.setText(it?.master_info)
        })

        vm.load()


        services.setOnClickListener {
                MAIN.navController.navigate(R.id.action_nav_profile_to_fragmentServicesList)
        }

        save.setOnClickListener {
            vm.save(
                UserInformation(
                    name = firstName.text.toString(),
                    surname = secondName.text.toString(),
                    phone_number = phone.text.toString(),
                    specialization = "специализация",
                    legal_information = "юр информ",
                    email = email.text.toString(),
                    master_info = info.text.toString()
                )
            )
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}