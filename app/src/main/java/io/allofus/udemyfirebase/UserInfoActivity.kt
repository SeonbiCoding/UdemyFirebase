package io.allofus.udemyfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserInfoActivity : AppCompatActivity() {

    var firstName: TextView? = null
    var lastName: TextView? = null
    var userName: TextView? = null
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        firstName = findViewById(R.id.tv_first_name)
        lastName = findViewById(R.id.tv_last_name)
        userName = findViewById(R.id.tv_user_name)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseAuth?.currentUser!!.uid)

        firebaseDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(task: DataSnapshot) {
                if (task.exists()) {
                    val firstname = task.child("firstNaame").value as String
                    val lastname = task.child("lastName").value as String
                    val username = task.child("userName").value as String

                    firstName?.text = firstname
                    lastName?.text = lastname
                    userName?.text = username
                }
            }

        })
    }

    public fun update(v: View) {
        startActivity(Intent(this@UserInfoActivity, DataActivity::class.java))

    }

    public fun change(v: View) {
        startActivity(Intent(this@UserInfoActivity, TestActivity::class.java))

    }

    fun upload(view: View) {
        startActivity(Intent(this@UserInfoActivity, StorageActivity::class.java))

    }

}
