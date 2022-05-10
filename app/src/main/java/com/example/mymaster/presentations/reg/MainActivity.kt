package com.example.mymaster.presentations.reg

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.Domain.models.UserInformation
import com.example.mymaster.presentations.mainMenu.MainMenu
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
    private lateinit var root: RelativeLayout

    private val vm by viewModel<MainActivityViewModel>()

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


                    startActivity(Intent(this@MainActivity, MainMenu::class.java))
                    finish()
                }.addOnFailureListener { e ->


                    Snackbar.make(
                        root, "Ошибка" + e.message,
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
                    val user = UserInformation()
                    user.email = email.text.toString()
                    user.name = name.text.toString()
                    user.uid = FirebaseAuth.getInstance().uid
                    //add in DataBase
                    masters.child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)
                        .addOnSuccessListener {
                            Snackbar.make(
                                root,
                                "Успешная регистрация",
                                Snackbar.LENGTH_SHORT
                            ).show()

                            auth.signInWithEmailAndPassword(user.email!!, pass.text.toString())
                                .addOnSuccessListener {
                                    startActivity(Intent(
                                        this@MainActivity, MainMenu::class.java)
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