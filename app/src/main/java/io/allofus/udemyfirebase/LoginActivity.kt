package io.allofus.udemyfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var user_email: EditText? = null
    private var user_password: EditText? = null
    private var btn_login: Button? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_email = findViewById(R.id.et_user_email_login)
        user_password = findViewById(R.id.et_user_password_login)
        btn_login = findViewById(R.id.btn_login_login)
        firebaseAuth = FirebaseAuth.getInstance()

        btn_login?.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {

        var email = user_email?.text.toString().trim()
        var password = user_password?.text.toString().trim()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "dd", Toast.LENGTH_SHORT).show()
        } else {

            firebaseAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "You are login successful", Toast.LENGTH_SHORT).show()
                } else {
                    val error = it.exception?.message
                    Toast.makeText(applicationContext, "Error $error", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun reset(view: View) {

        val intent = Intent(this@LoginActivity, PasswordResetActivity::class.java)
        startActivity(intent)

    }
}
