package com.example.mymaster.presentations.Reg

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.domain.Domain.models.*
import com.example.mymaster.presentations.Main3Activity
import com.example.mymaster.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rengwuxian.materialedittext.MaterialEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var masters: DatabaseReference
    private lateinit var root: ConstraintLayout

    private val vm by viewModel<MainViewModel>()
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(
                Intent(
                    this@MainActivity, Main3Activity::class.java
                )
            )
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val btnReg = findViewById<Button>(R.id.btnRegister)

        root = findViewById(R.id.root_element)
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        masters = db.getReference("Master")

        btnReg.setOnClickListener { showRegisterWindow() }
        btnSignIn.setOnClickListener { showSignInWindow() }
    }

    private fun showSignInWindow() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("??????????")
        dialog.setMessage("?????????????? ???????????????????? ?????? ??????????????????????")

        val inflater = LayoutInflater.from(this)
        val activitySigIn = inflater.inflate(R.layout.activity_signin, null)
        dialog.setView(activitySigIn)

        val email: MaterialEditText = activitySigIn.findViewById(R.id.emailField)
        val pass: MaterialEditText = activitySigIn.findViewById(R.id.passField)

        dialog.setPositiveButton("????????????") { dialogInterface, _ -> dialogInterface.dismiss() }
        dialog.setNegativeButton("OK", DialogInterface.OnClickListener { _, _ ->


            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(root, "?????????????? email", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (pass.text.toString().length < 5) {
                Snackbar.make(root, "?????????????????????? ????????????", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //vm.sigInWithEmail(email.text.toString(),pass.text.toString())

            auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnSuccessListener {
                    startActivity(Intent(this@MainActivity, Main3Activity::class.java))
                    finish()
                }.addOnFailureListener { e ->
                    Snackbar.make(
                        root, "???????????? ??????????????????????" + e.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        })
        dialog.show()
    }

    private fun showRegisterWindow() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("??????????????????????")
        dialog.setMessage("?????????????? ???????????????????? ?????? ??????????????????????")
        val inflater = LayoutInflater.from(this)
        val activityReg = inflater.inflate(R.layout.activity_reg, null)
        dialog.setView(activityReg)

        val email: MaterialEditText = activityReg.findViewById(R.id.emailField)
        val pass: MaterialEditText = activityReg.findViewById(R.id.passField)
        val name: MaterialEditText = activityReg.findViewById(R.id.nameField)

        //final MaterialEditText phone = activity_reg.findViewById(R.id.phoneField);

        dialog.setPositiveButton("????????????") { dialogInterface, _ -> dialogInterface.dismiss() }
        dialog.setNegativeButton("OK", DialogInterface.OnClickListener { _, _ ->
            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(root, "?????????????? email", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(name.text.toString())) {
                Snackbar.make(root, "?????????????? ??????", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (pass.text.toString().length < 5) {
                Snackbar.make(root, "?????????????????????? ????????????", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //registration users

            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnSuccessListener {

                   val newUser = NewUser(name.text.toString(),email.text.toString())
                    newUser.createNewUser()
                    //add in DataBase

                    val emailTemp = email.text.toString()

                    FirebaseDatabase.getInstance()
                        .getReference("Search")
                        .child("identificator")
                        .child(email.text.toString().replace(".","*",true))
                        .setValue(
                            FirebaseAuth.getInstance()
                                .currentUser?.uid.toString())

                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid).child("Schedule")
                        .setValue(newUser.scheduleSetting)

                    val key = masters.child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("Services").push().key
                    newUser.servicesModel.uidServices = key

                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid).child("Services")
                        .child(key.toString()).setValue(newUser.servicesModel)


                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid).child("Information")
                        .setValue(newUser.userInformation)
                        .addOnSuccessListener {


                            auth.signInWithEmailAndPassword(
                                newUser.userInformation.email!!,
                                pass.text.toString()
                            )
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@MainActivity, Main3Activity::class.java
                                        )
                                    )
                                    finish()
                                }.addOnFailureListener { e ->
                                    Snackbar.make(
                                        root,
                                        "????????????" + e.message,
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                        }
                }
        })
        dialog.show()
    }
}