package com.example.mymaster.presentations.scheduleActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.mymaster.Models.Clients
import com.example.mymaster.Models.RecordingSession
import com.example.mymaster.R
import com.example.mymaster.presentations.myProfileActivity.MyProfileViewModel
import com.example.mymaster.presentations.scheduleSettingActivity.ScheduleSettingActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    private var items: MutableList<RecordingSessionModel> = ArrayList()
    private val adapter: RecyclerView.Adapter<*> = ScheduleAdapter(items)
    private val vm by viewModel<ScheduleActivityViewModel>()
    private var rsDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Recording_Session")
    private var cDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Clients")

    private lateinit var mAuth: FirebaseAuth

    public override fun onStart() {
        mAuth = FirebaseAuth.getInstance()
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_main)

        val recyclerView = findViewById<RecyclerView>(R.id.sch_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.notifyItemInserted(items.size - 1)
        addRecyclerview()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivity(
                Intent(
                    this@ScheduleActivity,
                    ScheduleSettingActivity::class.java
                )
            )
        }

        vm.resultLive.observe(this,  {
            items = it
            addRecyclerview()
        })

        vm.getFriendList()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addRecyclerview() {
        items.sortWith(Comparator { o1, o2 ->
            var day01 = o1.data?.substring(0, o1.data!!.indexOf("."))
            var month01 =
                o1.data!!.substring(o1.data!!.indexOf(".") + 1, o1.data!!.indexOf(".", 3))
            val yearo1 =
                o1.data!!.substring(o1.data!!.indexOf(".", 3) + 1, o1.data!!.indexOf(".", 3) + 5)
            var day_02 = o2.data!!.substring(0, o2.data!!.indexOf("."))
            var month_02 =
                o2.data!!.substring(o2.data!!.indexOf(".") + 1, o2.data!!.indexOf(".", 3))
            val year_o2 =
                o2.data!!.substring(o2.data!!.indexOf(".", 3) + 1, o2.data!!.indexOf(".", 3) + 5)
            if (day01!!.toInt() < 10) {
                day01 = "0$day01"
            }
            if (day_02.toInt() < 10) {
                day_02 = "0$day_02"
            }
            if (month01.toInt() < 10) {
                month01 = "0$month01"
            }
            if (month_02.toInt() < 10) {
                month_02 = "0$month_02"
            }

            //проверь time
            (yearo1 + "." + month01 + "." + day01 + "." + o1.time).compareTo(
                year_o2 + "." + month_02 + "." + day_02 + "." + o2.time
            )
        })
        adapter.notifyDataSetChanged()
    }
}