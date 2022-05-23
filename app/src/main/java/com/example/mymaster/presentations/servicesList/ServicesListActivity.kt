package com.example.mymaster.presentations.servicesList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ServicesModel
import com.example.mymaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class ServicesListActivity : AppCompatActivity() {
    private var items: ArrayList<ServicesModel> = ArrayList()
    private var adapter: RecyclerView.Adapter<*> = ServicesAdapter(items)
    private val services: ArrayList<ServicesModel> = ArrayList()

    private val vm by viewModel<ServiceListActivityViewModel>()

    companion object {
        val names: MutableList<EditText> = ArrayList()
        val times: MutableList<EditText> = ArrayList()
        val prices: MutableList<EditText> = ArrayList()
    }

    private var mAuth: FirebaseAuth? = null
    public override fun onStart() {
        mAuth = FirebaseAuth.getInstance()
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            // reload();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_list)

        val recyclerView = findViewById<RecyclerView>(R.id.ser_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val save = findViewById<Button>(R.id.ser_btn_save)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener(View.OnClickListener {
            if (items.size > 14) return@OnClickListener
            items.add(ServicesModel())
            adapter.notifyItemInserted(items.size - 1)
        })

        vm.resultLive.observe(this, {
            items.clear()
            adapter.notifyDataSetChanged()
            it.forEach { i ->
                items.add(i)
            }
            adapter.notifyItemInserted(items.size - 1)
        })

        vm.load()

        save.setOnClickListener(View.OnClickListener {
            setServices()
            vm.save(services)
            services.clear()
            adapter.notifyDataSetChanged()
        })


    }

    private fun setServices() {

        for (i in items.indices) {
            if (names[i].text.toString() == "" && times[i].text.toString() == "") continue

            services.add(
                ServicesModel(
                    name = names[i].text.toString(),
                    price = prices[i].text.toString(),
                    timeInWork = times[i].text.toString(),
                    info = "",
                    status = true,
                    uidServices = items[i].uidServices
                )
            )
        }
    }

}