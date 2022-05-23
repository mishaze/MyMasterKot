package com.example.mymaster.presentations.scheduleSettingActivity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.domain.Domain.models.DayWeek
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.mymaster.MAIN
import com.example.mymaster.databinding.FragmentScheduleSettingBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FragmentPage(index: Int) : Fragment() {

    var cal = Calendar.getInstance()
    var mHour = cal[Calendar.HOUR_OF_DAY]
    var mMinute = cal[Calendar.MINUTE]

    var year = cal[Calendar.YEAR]
    var monthOfYear = cal[Calendar.MONTH]
    var dayOfMonth =cal[Calendar.DAY_OF_MONTH]



    private val index = index
    var textViews = ArrayList<TextView>()
    var schedule = ScheduleSettingModel()
    var checkSwitch = ArrayList<Switch>()

    var root: LinearLayout? = null

    private var mAuth: FirebaseAuth? = null
    private val vm by viewModel<ScheduleSettingActivityViewModel>()
    private var _binding: FragmentScheduleSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScheduleSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val save = binding.btnSchSave

        textViews.add(binding.schFrom1)
        textViews.add(binding.schFrom2)
        textViews.add(binding.schFrom3)
        textViews.add(binding.schFrom4)
        textViews.add(binding.schFrom5)
        textViews.add(binding.schFrom6)
        textViews.add(binding.schFrom7)

        textViews.add(binding.schTo1)
        textViews.add(binding.schTo2)
        textViews.add(binding.schTo3)
        textViews.add(binding.schTo4)
        textViews.add(binding.schTo5)
        textViews.add(binding.schTo6)
        textViews.add(binding.schTo7)

        checkSwitch.add(binding.schEnable1)
        checkSwitch.add(binding.schEnable2)
        checkSwitch.add(binding.schEnable3)
        checkSwitch.add(binding.schEnable4)
        checkSwitch.add(binding.schEnable5)
        checkSwitch.add(binding.schEnable6)
        checkSwitch.add(binding.schEnable7)

        textViews.forEach { onClickTime(it) }

        //dateFrom = findViewById<TextView>(R.id.sch_data_from);
        //dateTo = findViewById<TextView>(R.id.sch_data_to);
        vm.load()

        vm.resultLive.observe(this, {
            for (i in 1..it.num!!) {
                schedule = it
                textViews[0].text = it.dayOfWeek[index].monday?.start
                textViews[1].text = it.dayOfWeek[index].tuesday?.start
                textViews[2].text = it.dayOfWeek[index].wednesday?.start
                textViews[3].text = it.dayOfWeek[index].thursday?.start
                textViews[4].text = it.dayOfWeek[index].friday?.start
                textViews[5].text = it.dayOfWeek[index].sunday?.start
                textViews[6].text = it.dayOfWeek[index].saturday?.start

                textViews[7].text = it.dayOfWeek[index].monday?.end
                textViews[8].text = it.dayOfWeek[index].tuesday?.end
                textViews[9].text = it.dayOfWeek[index].wednesday?.end
                textViews[10].text = it.dayOfWeek[index].thursday?.end
                textViews[11].text = it.dayOfWeek[index].friday?.end
                textViews[12].text = it.dayOfWeek[index].sunday?.end
                textViews[13].text = it.dayOfWeek[index].saturday?.end

                checkSwitch[0].isChecked = it.dayOfWeek[index].monday?.check!!
                checkSwitch[1].isChecked = it.dayOfWeek[index].tuesday?.check!!
                checkSwitch[2].isChecked = it.dayOfWeek[index].wednesday?.check!!
                checkSwitch[3].isChecked = it.dayOfWeek[index].thursday?.check!!
                checkSwitch[4].isChecked = it.dayOfWeek[index].friday?.check!!
                checkSwitch[5].isChecked = it.dayOfWeek[index].sunday?.check!!
                checkSwitch[6].isChecked = it.dayOfWeek[index].saturday?.check!!

            }
        })

        save.setOnClickListener {
            setTime(textViews, checkSwitch)
            vm.save(schedule)
        }
        return root
    }

    private fun onClickTime(v: TextView) {
        v.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                MAIN,
                { _, hourOfDay, minute ->
                    val textViewTimeParam: String = if (minute < 10) {
                        "$hourOfDay:0$minute"
                    } else {
                        "$hourOfDay:$minute"
                    }
                    v.text = textViewTimeParam
                },
                mHour,
                mMinute,
                true
            )
            timePickerDialog.show()
        }
    }

    private fun setTime(v: ArrayList<TextView>, c: ArrayList<Switch>) {

        val dayOfWeek = DayWeek()

        dayOfWeek.monday?.start = v[0].text.toString()
        dayOfWeek.tuesday?.start = v[1].text.toString()
        dayOfWeek.wednesday?.start = v[2].text.toString()
        dayOfWeek.thursday?.start = v[3].text.toString()
        dayOfWeek.friday?.start = v[4].text.toString()
        dayOfWeek.sunday?.start = v[5].text.toString()
        dayOfWeek.saturday?.start = v[6].text.toString()

        dayOfWeek.monday?.end = v[7].text.toString()
        dayOfWeek.tuesday?.end = v[8].text.toString()
        dayOfWeek.wednesday?.end = v[9].text.toString()
        dayOfWeek.thursday?.end = v[10].text.toString()
        dayOfWeek.friday?.end = v[11].text.toString()
        dayOfWeek.sunday?.end = v[12].text.toString()
        dayOfWeek.saturday?.end = v[13].text.toString()

        schedule.dayOfWeek[index] = dayOfWeek
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

