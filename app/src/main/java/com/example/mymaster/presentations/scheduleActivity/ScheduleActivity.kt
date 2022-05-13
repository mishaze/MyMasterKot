package com.example.mymaster.presentations.scheduleActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.mymaster.R
import com.example.mymaster.presentations.scheduleSettingActivity.ScheduleSettingActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Thread.sleep
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    private var items: MutableList<RecordingSessionModel> = ArrayList()
    private val adapter: RecyclerView.Adapter<*> = ScheduleAdapter(items)
    private val vm by viewModel<ScheduleActivityViewModel>()
    private var mAuth = FirebaseAuth.getInstance()

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_main)

        val recyclerView = findViewById<RecyclerView>(R.id.sch_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.notifyItemInserted(items.size - 1)
        //addRecyclerview()

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

        vm.resultLive.observe(this, {
            items.clear()
            adapter.notifyDataSetChanged()
            it.forEach { i -> items.add(i) }
            //adapter.notifyItemInserted(items.size - 1)

            items.sortWith { o1, o2 -> (o1.date + "." + o1.time).compareTo(o2.date + "." + o2.time) }
            adapter.notifyDataSetChanged()
        })
        vm.getScheduleList()
    }


}