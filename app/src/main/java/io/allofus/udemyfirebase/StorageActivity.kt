package io.allofus.udemyfirebase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.*

class StorageActivity : AppCompatActivity() {

    var btn_upload: Button? = null
    var image_view: ImageView? = null
    var imageUri: Uri? = null
    private var storage: FirebaseStorage? = null
    private var imageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        btn_upload = findViewById(R.id.btn_upload_image)
        image_view = findViewById(R.id.imageView)
        storage = FirebaseStorage.getInstance()
        imageRef = storage?.reference?.child("Images")

        image_view?.setOnClickListener {
            pickImageFromGallery()
        }

        btn_upload?.setOnClickListener {

            addImageToFirebase()
        }
    }

    private fun pickImageFromGallery() {
        val gallery = Intent()
        gallery.type = "image/*"
        gallery.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(gallery, 111)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data

            try {
                val image = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                image_view?.setImageBitmap(image)
            } catch (error: IOException) {
                Toast.makeText(applicationContext, "Error $error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addImageToFirebase() {

        if (imageUri != null) {

            val ref = imageRef?.child(UUID.randomUUID().toString())
            ref?.putFile(imageUri!!)?.addOnCompleteListener(object : OnCompleteListener<UploadTask.TaskSnapshot> {
                override fun onComplete(task: Task<UploadTask.TaskSnapshot>) {
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Image Uploaded Fine :)", Toast.LENGTH_SHORT).show()
                    } else {
                        val error = task.exception?.message
                        Toast.makeText(applicationContext, "Error $error", Toast.LENGTH_SHORT).show()
                    }
                }

            })


        }

    }
}
