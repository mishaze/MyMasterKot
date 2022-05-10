package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.FriendStorage
import com.example.domain.Domain.models.ClientInform
import com.example.domain.Domain.models.responses.ResponseListFriendMaster
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class SharedPrefUserFriend(private val mDb: DatabaseReference) : FriendStorage {


    override fun addInMasterFriend(email: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Clients")
        val dataQuery = dbRef.orderByChild("email").equalTo(email)

        dataQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val clients: ClientInform =
                        ds.getValue(ClientInform::class.java)!!

                    if (!checkRepeatUidInFriendList(uid=clients.uid.toString())) {
                        mDb!!.child("friends").push().setValue(clients.uid)

                        FirebaseDatabase.getInstance().getReference("Client")
                            .child(clients.uid.toString()).child("friends").push().setValue(
                                Objects.requireNonNull(
                                    FirebaseAuth.getInstance().currentUser
                                )!!.uid
                            )
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    override fun getListMasterFriend(callback: com.example.domain.Domain.models.responses.FirebaseCallback<com.example.domain.Domain.models.responses.ResponseListFriendMaster>) {

        mDb.addListenerForSingleValueEvent(object : ValueEventListener {
            val friends = ArrayList<String?>()
            val response: ResponseListFriendMaster = ResponseListFriendMaster()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.child("friends").children) {
                    val friend: String? = ds.getValue(String::class.java)
                    friends.add(friend)
                }
                val mCls: DatabaseReference = FirebaseDatabase.getInstance().getReference("Clients")
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


    fun checkRepeatUidInFriendList(
        uid: String,
        place: String = "Masters",
        uidPlace: String = FirebaseAuth.getInstance().currentUser!!.uid
    ): Boolean {
        val mDatabase: DatabaseReference =
            FirebaseDatabase.getInstance().getReference(place).child(uidPlace)

        val friends = ArrayList<String>()
        mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.child("friends").children) {
                    val friend: String? = ds.getValue(String::class.java)
                    if (friend != null) {
                        friends.add(friend)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        return friends.contains(uid)
    }
}