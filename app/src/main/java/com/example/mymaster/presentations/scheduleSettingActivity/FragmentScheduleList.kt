package com.example.mymaster.presentations.scheduleSettingActivity
/*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.mymaster.R
import com.example.mymaster.databinding.FragmentMyProfileBinding
import com.example.mymaster.databinding.FragmentScheduleBinding
import com.example.mymaster.databinding.FragmentServicesListBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FragmentScheduleList:Fragment() {

    var cal = Calendar.getInstance()
    var mHour = cal[Calendar.HOUR_OF_DAY]
    var mMinute = cal[Calendar.MINUTE]
    
    var textViews = ArrayList<TextView>()
    var schedule = ArrayList<ScheduleSettingModel>()
    var checkBoxes = ArrayList<CheckBox>()

    var root: LinearLayout? = null
    
    private var mAuth: FirebaseAuth? = null
    private val vm by viewModel<ScheduleSettingActivityViewModel>()
    private var _binding: FragmentServicesListBinding? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScheduleList.inflate(inflater, container, false)
        val root: View = binding.root



        val save = findViewById<Button>(R.id.btn_sch_Save)

        root = findViewById(R.id.root_sch_set)

        textViews.add(findViewById<View>(R.id.sch_from1) as TextView)
        textViews.add(findViewById<View>(R.id.sch_from2) as TextView)
        textViews.add(findViewById<View>(R.id.sch_from3) as TextView)
        textViews.add(findViewById<View>(R.id.sch_from4) as TextView)
        textViews.add(findViewById<View>(R.id.sch_from5) as TextView)
        textViews.add(findViewById<View>(R.id.sch_from6) as TextView)
        textViews.add(findViewById<View>(R.id.sch_from7) as TextView)

        textViews.add(findViewById<View>(R.id.sch_to1) as TextView)
        textViews.add(findViewById<View>(R.id.sch_to2) as TextView)
        textViews.add(findViewById<View>(R.id.sch_to3) as TextView)
        textViews.add(findViewById<View>(R.id.sch_to4) as TextView)
        textViews.add(findViewById<View>(R.id.sch_to5) as TextView)
        textViews.add(findViewById<View>(R.id.sch_to6) as TextView)
        textViews.add(findViewById<View>(R.id.sch_to7) as TextView)

        checkBoxes.add(findViewById<View>(R.id.sch_enable1) as CheckBox)
        checkBoxes.add(findViewById<View>(R.id.sch_enable2) as CheckBox)
        checkBoxes.add(findViewById<View>(R.id.sch_enable3) as CheckBox)
        checkBoxes.add(findViewById<View>(R.id.sch_enable4) as CheckBox)
        checkBoxes.add(findViewById<View>(R.id.sch_enable5) as CheckBox)
        checkBoxes.add(findViewById<View>(R.id.sch_enable6) as CheckBox)
        checkBoxes.add(findViewById<View>(R.id.sch_enable7) as CheckBox)

        textViews.forEach{onClickTime(it)}

        //dateFrom = findViewById<TextView>(R.id.sch_data_from);
        //dateTo = findViewById<TextView>(R.id.sch_data_to);

        vm.resultLive.observe(this,  {
            for(i in 1..it.num!!)
            {
                textViews[i].text = " "
                textViews[i + 7].text = " "
                checkBoxes[i].isChecked = true
            }
        })

        save?.setOnClickListener {
            setTime(textViews, checkBoxes)
            vm.save(schedule)
        }
        return root
    }
}
*/