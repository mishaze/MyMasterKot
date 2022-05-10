package com.example.mymaster.presentations.myProfileActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Domain.models.UserInformation
import com.example.mymaster.R
import com.example.mymaster.presentations.servicesListActivity.ServicesListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyProfile : AppCompatActivity() {

    private val mDatabase = FirebaseDatabase
        .getInstance()
        .getReference("Master")
        .child("1")
        .child("Information")

    private val vm by viewModel<MyProfileViewModel>()

    private var mAuth = FirebaseAuth.getInstance()

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        Log.e("AAA", "Activity Create")

        //vm = ViewModelProvider(
        //   this,
        //    MyProfileViewModelFactory(mDatabase)
        // ).get(MyProfileViewModel::class.java)

        val firstName = findViewById<EditText>(R.id.mpFirstname)
        val secondName = findViewById<EditText>(R.id.mpName)
        val phone = findViewById<EditText>(R.id.mpPhone)
        val email = findViewById<EditText>(R.id.mpEmail)
        email.isEnabled = false

        val address = findViewById<EditText>(R.id.mpAddress)
        val info = findViewById<EditText>(R.id.mpInfo)
        val services = findViewById<Button>(R.id.mp_btn_services)
        val save = findViewById<Button>(R.id.mp_btn_save)

        vm.resultLive.observe(this, Observer {
            firstName.setText(it?.name)

        })

        vm.load()

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

        services.setOnClickListener {
            startActivity(
                Intent(
                    this@MyProfile,
                    ServicesListActivity::class.java
                )
            )
        }
    }
}