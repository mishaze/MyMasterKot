package com.example.mymaster.presentations.mainMenu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mymaster.presentations.friendActivity.FriendActivity
import com.example.mymaster.presentations.myProfileActivity.MyProfile
import com.example.mymaster.R
import com.example.mymaster.presentations.scheduleActivity.ScheduleActivity
import com.example.mymaster.presentations.settingsActivity.SettingsActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging


class MainMenu : AppCompatActivity() {
    private lateinit  var mAuth: FirebaseAuth

    public override fun onStart() {
        mAuth = FirebaseAuth.getInstance()
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val btnSchedule = findViewById<Button>(R.id.btnScheduler)
        val btnClients = findViewById<Button>(R.id.btnClients)
        val btnMyProfile = findViewById<Button>(R.id.btnMyProfil)
        val btnSetting = findViewById<Button>(R.id.btnSetting)
        val btnHelp = findViewById<Button>(R.id.btnHelp)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        btnSchedule.setOnClickListener{
            startActivity(Intent(this@MainMenu, ScheduleActivity::class.java))}
        btnClients.setOnClickListener{
            startActivity(Intent(this@MainMenu, FriendActivity::class.java)) }
        btnSetting.setOnClickListener {
            startActivity(Intent(this@MainMenu, SettingsActivity::class.java)) }
        btnMyProfile.setOnClickListener{
            startActivity(Intent(this@MainMenu, MyProfile::class.java)) }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast
            Toast.makeText(this@MainMenu, token, Toast.LENGTH_SHORT).show()
            Log.d("TAG", token!!)
        })
    }
}