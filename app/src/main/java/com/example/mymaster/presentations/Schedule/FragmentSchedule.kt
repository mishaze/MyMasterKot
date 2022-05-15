package com.example.mymaster.presentations.Schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.mymaster.databinding.FragmentScheduleBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class FragmentSchedule:Fragment() {
    private var items: MutableList<RecordingSessionModel> = ArrayList()
    private val adapter: RecyclerView.Adapter<*> = ScheduleAdapter(items)
    private val vm by viewModel<ScheduleActivityViewModel>()
    private var mAuth = FirebaseAuth.getInstance()

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val recyclerView = binding.schList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.notifyItemInserted(items.size - 1)
        //addRecyclerview()
/*
        val toolbar = binding.toolbar
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
*/
        vm.resultLive.observe(this, {
            items.clear()
            adapter.notifyDataSetChanged()
            it.forEach { i -> items.add(i) }
            //adapter.notifyItemInserted(items.size - 1)

            items.sortWith { o1, o2 -> (o1.date + "." + o1.time).compareTo(o2.date + "." + o2.time) }
            adapter.notifyDataSetChanged()
        })
        vm.getScheduleList()

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}