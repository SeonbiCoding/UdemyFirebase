package io.allofus.udemyfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DataActivity : AppCompatActivity() {

    var user_first_name: EditText? = null
    var user_last_name: EditText? = null
    var user_username: EditText? = null
    var btn_update: Button? = null
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        user_first_name = findViewById(R.id.tv_first_name)
        user_last_name = findViewById(R.id.tv_last_name)
        user_username = findViewById(R.id.tv_user_name)
        btn_update = findViewById(R.id.btn_update)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseAuth?.currentUser!!.uid)

        btn_update?.setOnClickListener {

            saveUserInfo()
        }


    }

    private fun saveUserInfo() {

        val firstname = user_first_name?.text.toString().trim()
        val lastname = user_last_name?.text.toString().trim()
        val username = user_username?.text.toString().trim()

        if (TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(username)) {
            Toast.makeText(applicationContext, "Please Enter something in fields", Toast.LENGTH_SHORT).show()
        } else {
            val userinfo = HashMap<String, Any>()
            userinfo.put("firstNaame", firstname)
            userinfo.put("lastName", lastname)
            userinfo.put("userName", username)

            firebaseDatabase?.updateChildren(userinfo)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Your Information was updated", Toast.LENGTH_SHORT).show()

                } else {
                    val error = it.exception?.message
                    Toast.makeText(applicationContext, "Error : $error", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}