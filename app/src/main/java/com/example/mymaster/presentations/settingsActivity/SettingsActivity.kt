package com.example.mymaster.presentations.settingsActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mymaster.R
import com.example.mymaster.presentations.reg.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
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
        setContentView(R.layout.settings_activity)
        val signOutBtn = findViewById<Button>(R.id.btn_sign_out)

        signOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@SettingsActivity, MainActivity::class.java))
            finish()
        }
    }
}