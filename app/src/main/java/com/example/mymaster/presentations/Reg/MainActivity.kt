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
        dialog.setTitle("Войти")
        dialog.setMessage("Введите информацию для авториции")

        val inflater = LayoutInflater.from(this)
        val activitySigIn = inflater.inflate(R.layout.activity_signin, null)
        dialog.setView(activitySigIn)

        val email: MaterialEditText = activitySigIn.findViewById(R.id.emailField)
        val pass: MaterialEditText = activitySigIn.findViewById(R.id.passField)

        dialog.setPositiveButton("Отмена") { dialogInterface, _ -> dialogInterface.dismiss() }
        dialog.setNegativeButton("OK", DialogInterface.OnClickListener { _, _ ->


            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(root, "Введите email", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (pass.text.toString().length < 5) {
                Snackbar.make(root, "Некоректный пароль", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //vm.sigInWithEmail(email.text.toString(),pass.text.toString())

            auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnSuccessListener {
                    startActivity(Intent(this@MainActivity, Main3Activity::class.java))
                    finish()
                }.addOnFailureListener { e ->
                    Snackbar.make(
                        root, "Ошибка Авторизации" + e.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        })
        dialog.show()
    }

    private fun showRegisterWindow() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Регистрация")
        dialog.setMessage("Введите информацию для регистрации")
        val inflater = LayoutInflater.from(this)
        val activityReg = inflater.inflate(R.layout.activity_reg, null)
        dialog.setView(activityReg)

        val email: MaterialEditText = activityReg.findViewById(R.id.emailField)
        val pass: MaterialEditText = activityReg.findViewById(R.id.passField)
        val name: MaterialEditText = activityReg.findViewById(R.id.nameField)

        //final MaterialEditText phone = activity_reg.findViewById(R.id.phoneField);

        dialog.setPositiveButton("Отмена") { dialogInterface, _ -> dialogInterface.dismiss() }
        dialog.setNegativeButton("OK", DialogInterface.OnClickListener { _, _ ->
            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(root, "Введите email", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(name.text.toString())) {
                Snackbar.make(root, "Введите имя", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (pass.text.toString().length < 5) {
                Snackbar.make(root, "Некоректный пароль", Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //registration users

            //vm.regWithEmail(email.text.toString(), pass.text.toString(),name.text.toString())

            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnSuccessListener {
                    val userInformation = UserInformation(
                        uid = FirebaseAuth.getInstance().currentUser!!.uid,
                        name = name.text.toString(),
                        surname = "Петров",
                        phone_number = "+79507352828",
                        specialization = "Мастер по маникюру",
                        legal_information = "ИНН:5444564115155514",
                        email = email.text.toString(),
                        master_info = "Занимаюсь маникюром и все что с этим связано"
                    )
                    userInformation.email = email.text.toString()
                    userInformation.name = name.text.toString()
                    userInformation.uid = FirebaseAuth.getInstance().uid


                    val temp1: ArrayList<DayWeek> = ArrayList()

                    temp1.add(
                        DayWeek(
                            monday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            tuesday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            wednesday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            thursday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            friday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            sunday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            saturday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            )
                        )
                    )

                    temp1.add(
                        DayWeek(
                            monday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            tuesday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            wednesday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            thursday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            friday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            sunday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            ),
                            saturday = Day(
                                start = "08:00",
                                end = "23:00",
                                start_dinner = "00:00",
                                end_dinner = "00:00"
                            )
                        )
                    )

                    val scheduleSetting =
                        ScheduleSettingModel(time = "01:50", num = 1, dayOfWeek = temp1)
                    val servicesModel = ServicesModel(
                        name = "Маникюр",
                        price = "1000",
                        timeInWork = "120",
                        info = "Ноготочки делаю я тут вот так вот",
                        uidServices = "",
                        status = true
                    )

                    //add in DataBase

                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid).child("Schedule")
                        .setValue(scheduleSetting)

                    val key = masters.child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child("Services").push().key
                    servicesModel.uidServices = key

                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid).child("Services")
                        .child(key.toString()).setValue(servicesModel)

                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid).child("Information")
                        .setValue(userInformation)
                        .addOnSuccessListener {


                            auth.signInWithEmailAndPassword(
                                userInformation.email!!,
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
                                        "Ошибка" + e.message,
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                        }
                }
        })
        dialog.show()
    }
}