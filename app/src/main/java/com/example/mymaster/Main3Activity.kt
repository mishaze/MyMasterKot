package com.example.mymaster

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.RemoteViews
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import com.example.mymaster.databinding.ActivityMain3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Main3Activity : AppCompatActivity() {

    private lateinit var broadcastReceiver: BroadcastReceiver
    private var check: Boolean = false
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain3Binding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        MAIN = this

        setSupportActionBar(binding.appBarMain3.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_slideshow, R.id.nav_setting
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

/*
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
               val extras = intent?.extras

            }

        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(FirebaseMessagingService.INTENT_FILTER)
        registerReceiver(broadcastReceiver, intentFilter)
*/

        val mrDatabase: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("MasterRecords")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val notificationHelper = NotificationHelper()

        mrDatabase.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (check)
                notificationHelper.sendNotification(MAIN, "Новая запись", "Просмотрите ваше расписание к вам кто-то записался")
                else
                    check = true
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun notification(){


    }
}