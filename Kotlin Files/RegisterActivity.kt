package com.example.rajatmonga.kotlinchatapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener {

            performRegister()
        }




        already_have_account_text_view.setOnClickListener {
            Log.d("RegisterActivity", "try to show login activity")

            // launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        selectphoto_button_register.setOnClickListener{
            Log.d("RegisterActivity", "try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 0)


        }


    }

    private val selectedPhotoUri: Uri? = null


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RegisterActivity", "select photo was okay")
        }


        val selectedPhotoUri = data?.data //location of where image is stored on device
        Log.d("register", "selectedPhotoUri $selectedPhotoUri")

        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri) //bitmap of selected

        val bitmapDrawable = BitmapDrawable(bitmap)
        selectphoto_button_register.setBackgroundDrawable(bitmapDrawable)

    }


    private fun performRegister() {

        val email = email_edit_text_register.text.toString()
        val password = password_edit_text_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return

        }

        Log.d( "RegisterActivity", "email is " + email)
        Log.d( "RegisterActivity", "password is  $password")

        //Firebase authentication with email, username, and password

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //else if successful

                    Log.d( "RegisterActivity", "createdUser with uid: ${it.result.user.uid}")

                    uploadImageToFirebaseStorage()

                }
                .addOnFailureListener {
                    Log.d( "RegisterActivity", "Failed to create User: ${it.message}")
                    Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
                }

    }

    private fun uploadImageToFirebaseStorage() {

        if (selectedPhotoUri == null){
            //Log.d("register", "selectedPhotoUri $selectedPhotoUri")
            return
        }
        Log.d("register", "selectedPhotoUri $selectedPhotoUri")
        val filename = UUID.randomUUID().toString()

        val ref = FirebaseStorage.getInstance().getReference("/image/$filename")
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("RegisterActivity", "Uploaded Image Successfully: ${it.metadata?.path}")
                }



    }

}
