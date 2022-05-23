package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.FriendStorage
import com.example.domain.Domain.models.ClientInform
import com.example.domain.Domain.models.responses.ResponseListFriendMaster
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class SharedPrefUserFriend() : FriendStorage {


    override fun addInMasterFriend(email: String) {


        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Master").child(FirebaseAuth.getInstance().currentUser!!.uid)



        mDatabase.child("friends").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val friends = ArrayList<String>()
                for (ds in snapshot.children) {
                    val friend: String? = ds.getValue(String::class.java)
                    if (friend != null) {
                        friends.add(friend)
                    }
                }

                if (friends.contains(email)) return

                val dbRef = FirebaseDatabase.getInstance().getReference("Client")
                val dataQuery = dbRef.orderByChild("email").equalTo(email)

                dataQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds in snapshot.children) {
                            val clients: ClientInform = ds.getValue(ClientInform::class.java)!!


                            FirebaseDatabase.getInstance()
                                .getReference("Master")
                                .child(
                                    FirebaseAuth.getInstance()
                                        .currentUser?.uid.toString()
                                ).child("friends").push().setValue(clients.uid)

                        }
                    }
                    override fun onCancelled(databaseError: DatabaseError) {}
                })

            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun getListMasterFriend(callback: com.example.domain.Domain.models.responses.FirebaseCallback<com.example.domain.Domain.models.responses.ResponseListFriendMaster>) {

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("friends")
            .addListenerForSingleValueEvent(object : ValueEventListener {
            val friends = ArrayList<String?>()
            val response: ResponseListFriendMaster = ResponseListFriendMaster()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val friend: String? = ds.getValue(String::class.java)
                    friends.add(friend)
                }
                val mCls: DatabaseReference = FirebaseDatabase.getInstance().getReference("Client")
                mCls.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds in snapshot.children) {
                            val friend = ds.getValue(ClientInform::class.java)
                            if (friends.contains(friend?.uid)) {

                                ds.getValue(ClientInform::class.java)
                                    ?.let { response.answer.add(it) }
                            }
                        }
                        callback.onResponse(response)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}