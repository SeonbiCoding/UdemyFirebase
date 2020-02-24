package io.allofus.udemyfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView

class UserInfoActivity : AppCompatActivity() {

    var firstName: TextView? = null
    var lastName: TextView? = null
    var userName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        firstName = findViewById(R.id.tv_first_name)
        lastName = findViewById(R.id.tv_last_name)
        userName = findViewById(R.id.tv_user_name)


    }

    public fun update(v: View) {
        startActivity(Intent(this@UserInfoActivity, DataActivity::class.java))

    }

    public fun change(v: View) {
        startActivity(Intent(this@UserInfoActivity, TestActivity::class.java))

    }

}
