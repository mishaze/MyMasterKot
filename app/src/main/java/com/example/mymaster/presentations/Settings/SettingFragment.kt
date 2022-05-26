package com.example.mymaster.presentations.Settings

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.Domain.models.SettingModel
import com.example.mymaster.MAIN
import com.example.mymaster.databinding.FragmentSettingsBinding
import com.example.mymaster.presentations.Reg.MainActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class SettingFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var cal = Calendar.getInstance()
    private var mHour = cal[Calendar.HOUR_OF_DAY]
    private var mMinute = cal[Calendar.MINUTE]

    private val vm by viewModel<SettingViewModel>()

    private var setting: SettingModel = SettingModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val signOutBtn = binding.btnSignOut
        val btn1 = binding.btn1
        val btn2 = binding.btn2
        val clock = binding.clock
        vm.load()

        clock.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                MAIN,
                { _, hourOfDay, minute ->
                    val textViewTimeParam: String = if (minute < 10) {
                        "$hourOfDay:0$minute"
                    } else {
                        "$hourOfDay:$minute"
                    }
                    clock.text = textViewTimeParam
                    setting.time = textViewTimeParam
                    vm.save(setting)
                },
                mHour,
                mMinute,
                true
            )
            timePickerDialog.show()
        }


        clock.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(
                MAIN, { _, _, month1, day1 ->

                    val textDay:String = if (day1 <10){"0$day1"}else{"$day1"}

                    val textMonth:String = if (month1 <10){"0$month1"}else{"$month1"}

                    val textViewDataParam: String = "$textDay:$textMonth"

                    clock.text=textViewDataParam
                    setting.time = textViewDataParam
                    vm.save(setting)

                }, year, month, day
            )
            datePickerDialog.show()
        }

        btn1.setOnClickListener {
            setting.num = 1
            vm.save(setting)
            btn1.isEnabled = false
            btn2.isEnabled = true
        }

        btn2.setOnClickListener {
            setting.num = 2
            vm.save(setting)
            btn1.isEnabled = true
            btn2.isEnabled = false
        }

        vm.resultLive.observe(this, {
            setting = it!!
            if (it.num == 1) {
                btn1.isEnabled = false
                btn2.isEnabled = true
            } else {
                btn1.isEnabled = true
                btn2.isEnabled = false
            }

            clock.text = it.time
        })


        signOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(MAIN, MainActivity::class.java))
            MAIN.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}