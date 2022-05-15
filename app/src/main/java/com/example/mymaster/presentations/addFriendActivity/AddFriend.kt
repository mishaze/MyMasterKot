package com.example.mymaster.presentations.addFriendActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mymaster.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFriend : AppCompatActivity() {
    var email:EditText?= null
    private var mDb: DatabaseReference? = null
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

    private val vm by viewModel<AddFriendActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        val uid = findViewById<TextView>(R.id.Uid)
        val email = findViewById<EditText>(R.id.Uid_friend)
        val addFriend = findViewById<Button>(R.id.btn_add_friend)

        uid.text = "Введите Email пользователя"

        addFriend.setOnClickListener(View.OnClickListener {
            vm.addFriend(email.text.toString())
        })
    }

}