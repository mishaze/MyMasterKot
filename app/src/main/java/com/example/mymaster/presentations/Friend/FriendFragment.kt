package com.example.mymaster.presentations.Friend

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ClientInform
import com.example.mymaster.MAIN
import com.example.mymaster.R
import com.example.mymaster.databinding.FragmentFriendBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.rengwuxian.materialedittext.MaterialEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class FriendFragment : Fragment() {

    private var items: MutableList<ClientInform> = ArrayList()
    private val adapter: RecyclerView.Adapter<*> = FriendAdapter(items)
    private var mAuth: FirebaseAuth? = null

    private val vm by viewModel<FriendViewModel>()
    private var _binding: FragmentFriendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.friendList
        val fab = binding.fab

        recyclerView.layoutManager = LinearLayoutManager(context)
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
        /*
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { startActivity(Intent(this@FriendActivity, AddFriend::class.java)) }
        */
        vm.resultLive.observe(this, {

            it.forEach { items.add(it) }
            adapter.notifyItemInserted(items.size - 1)
        }
        )

        fab.setOnClickListener {
            showSignInWindow()
        }
        vm.getFriendList()

        return root
    }

    private fun showSignInWindow() {
        val dialog = AlertDialog.Builder(MAIN)
        dialog.setTitle("Войти")
        dialog.setMessage("Введите информацию для авториции")

        val inflater = LayoutInflater.from(MAIN)
        val activitySigIn = inflater.inflate(R.layout.add_friend, null)
        dialog.setView(activitySigIn)

        val email: MaterialEditText = activitySigIn.findViewById(R.id.addfriend)


        dialog.setPositiveButton("Отмена") { dialogInterface, _ -> dialogInterface.dismiss() }
        dialog.setNegativeButton("OK", DialogInterface.OnClickListener { _, _ ->
            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(binding.root, "Введите email", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            vm.addFriend(email.text.toString())
            vm.getFriendList()
        })
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}