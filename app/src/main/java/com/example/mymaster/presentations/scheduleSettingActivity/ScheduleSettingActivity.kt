package com.example.mymaster.presentations.scheduleSettingActivity

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mymaster.Models.Schedule
import com.example.mymaster.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class ScheduleSettingActivity : AppCompatActivity() {
    var cal = Calendar.getInstance()
    var mHour = cal[Calendar.HOUR_OF_DAY]
    var mMinute = cal[Calendar.MINUTE]


    private var mDatabase: DatabaseReference? = null
    var textViews = ArrayList<TextView>()
    var schedule = ArrayList<Schedule>()
    var checkBoxes = ArrayList<CheckBox>()

    var root: LinearLayout? = null
    private var mAuth: FirebaseAuth? = null

    public override fun onStart() {
        mAuth = FirebaseAuth.getInstance()
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_setting)

        mDatabase = FirebaseDatabase.getInstance().getReference("Masters").child(
            Objects.requireNonNull(FirebaseAuth.getInstance().currentUser)!!.uid
        )
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

        //dateFrom = findViewById<TextView>(R.id.sch_data_from);
        //dateTo = findViewById<TextView>(R.id.sch_data_to);

        mDatabase!!.addListenerForSingleValueEvent(object : ValueEventListener {
            var i = 0
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.child("schedule").children) {
                    val sch = ds.getValue(Schedule::class.java)!!
                    textViews[i].text = timeUnParse(sch.time_start)
                    textViews[i + 7].text = timeUnParse(sch.time_finish)
                    checkBoxes[i].isChecked = sch.isEnable
                    i++
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        //onClickDate(dateFrom);
        //onClickDate(dateTo);
        for (v in textViews) {
            onClickTime(v)
        }
        save?.setOnClickListener {
            setTime(textViews, checkBoxes)
            mDatabase!!.child("schedule")
                .setValue(schedule)
                .addOnSuccessListener {
                   // Snackbar.make(this.root, "Расписание добавлено", Snackbar.LENGTH_SHORT)
                     //   .show()
                    //schedule.clear()
                }
        }
    }

    private fun onClickTime(v: TextView) {
        v.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this@ScheduleSettingActivity,
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


    private fun setTime(
        v: ArrayList<TextView>,
        c: ArrayList<CheckBox>
    ) {
        for (i in 0..6) {
            val tempStart = v[i].text.toString()
            val tempStop = v[i + 7].text.toString()
            val timeStart = timeParse(tempStart) //+ (24 * 60 * i);
            val timeStop = timeParse(tempStop) // + (24 * 60 * i);
            schedule.add(Schedule(timeStart, timeStop, c[i].isChecked))
        }
    }

    private fun timeParse(temp: String): Int {
        var time = 0
        if (temp.indexOf(':') == 1) {
            time = temp.substring(0, 1).toInt() * 60
            time += temp.substring(2, 4).toInt()
        } else if (temp.indexOf(':') == 2) {
            time = temp.substring(0, 2).toInt() * 60
            time += temp.substring(3, 5).toInt()
        }
        return time
    }

    private fun timeUnParse(temp: Int): String {
        var minute = 0
        var hours = 0
        hours = temp / 60
        minute = temp - hours * 60
        val sminute: String = if (minute == 0) {
            "00"
        } else if (minute < 10) {
            "0$minute"
        } else {
            minute.toString()
        }
        val shours: String = if (hours == 0) {
            "00"
        } else if (hours < 10) {
            "0$hours"
        } else {
            hours.toString()
        }
        return "$shours:$sminute"
    }
}