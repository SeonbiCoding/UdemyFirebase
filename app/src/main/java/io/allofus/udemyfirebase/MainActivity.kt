package io.allofus.udemyfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var btn_signup: Button? = null
    private var et_user_email: EditText? = null
    private var et_user_passowrd: EditText? = null
    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_signup = findViewById(R.id.btn_signup)
        et_user_email = findViewById(R.id.et_user_email)
        et_user_passowrd = findViewById(R.id.et_user_password)
        firebaseAuth = FirebaseAuth.getInstance()

        btn_signup?.setOnClickListener {

            registerNewUser()
        }


    }

    private fun registerNewUser() {

        var email = et_user_email?.text.toString().trim()
        var password = et_user_passowrd?.text.toString().trim()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "These fields can not be empty", Toast.LENGTH_SHORT).show()

        } else {
            firebaseAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {

                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Account Created", Toast.LENGTH_SHORT).show()
                    } else {
                        val error = task.exception?.message
                        Toast.makeText(applicationContext, "Error $error", Toast.LENGTH_SHORT).show()
                    }
                }

            })

        }

    }

    fun login(view: View) {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}
