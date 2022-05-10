package com.example.mymaster.presentations.friendActivity

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ClientInform
import com.example.mymaster.presentations.addFriendActivity.AddFriend
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
import com.example.mymaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FriendActivity : AppCompatActivity() {
    private var items: MutableList<ClientInform> = ArrayList()
    private val adapter: RecyclerView.Adapter<*> = FriendAdapter(items)
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

    private val vm by viewModel<FriendActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)

        val recyclerView = findViewById<RecyclerView>(R.id.friend_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        /*
        this.items.add(new Clients("Михаил","Зусман","misha@list.ru","88945654564"));
        this.items.add(new Clients("Анастасия","Егорова","anastas@list.ru","88945656564"));
        this.items.add(new Clients("Екатерина","Иванова","kkkatusha@mail.ru","88985656564"));
        this.items.add(new Clients("Алексей","Петрова","LeXXattt@gmail.com","88945656564"));
        this.items.add(new Clients("Арсений","Михайлов","arssila@list.ru","88945888564"));
        this.items.add(new Clients("Петр","Третьяков","pppetrrrr@list.ru","88945656964"));
        this.items.add(new Clients("Василий","Гайлер","vasiyaa@list.ru","88945656514"));
        */
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { startActivity(Intent(this@FriendActivity, AddFriend::class.java)) }

        vm.resultLive.observe(this,{

            items = it
            adapter.notifyItemInserted(items.size - 1)
        }
        )

        vm.getFriendList()
    }

}