package io.allofus.udemyfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : AppCompatActivity() {

    private var user_email: EditText? = null
    private var btn_reset: Button? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        user_email = findViewById(R.id.et_user_email_reset)
        btn_reset = findViewById(R.id.btn_reset)
        firebaseAuth = FirebaseAuth.getInstance()

        btn_reset?.setOnClickListener {
            reset()
        }
    }

    private fun reset() {

        var email = user_email?.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter your email", Toast.LENGTH_SHORT).show()

        } else {

            firebaseAuth?.sendPasswordResetEmail(email)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Please check your email for password reset", Toast.LENGTH_SHORT).show()
                } else {
                    val error = it.exception?.message
                    Toast.makeText(applicationContext, "Error $error", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
