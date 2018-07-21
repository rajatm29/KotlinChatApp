package com.example.rajatmonga.kotlinchatapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rajatmonga.kotlinchatapp.R.id.email_edit_text_register
import com.example.rajatmonga.kotlinchatapp.R.id.password_edit_text_register
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {

            val email = email_edit_text_register.text.toString()
            val password = password_edit_text_register.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()


            }

            Log.d( "MainActivity", "email is " + email)
            Log.d( "MainActivity", "password is  $password")

            //Firebase authentication with email, username, and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword("9199999989@pintech.com", "corrfecthorsebatterystaple")
                    .addOnCompleteListener {
                        if(!it.isSuccessful) return@addOnCompleteListener

                        //else if successful

                        Log.d( "Main", "createdUser with uid: ${it.result.user.uid}")

                    }.addOnFailureListener {
                Log.d( "Main", "Failed to create User: ${it.message}")
                Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            }


            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)


                    .addOnCompleteListener {
                        if(!it.isSuccessful) return@addOnCompleteListener

                        //else if successful

                        Log.d( "Main", "createdUser with uid: ${it.result.user.uid}")

                    }.addOnFailureListener {
                        Log.d( "Main", "Failed to create User: ${it.message}")
                        Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
                    }


            Log.d("MainActivity", "after create")
        }





        already_have_account_text_view.setOnClickListener {
            Log.d("MainActivity", "try to show login activity")

            // launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }


    private fun performRegister() {

        val email = email_edit_text_register.text.toString()
        val password = password_edit_text_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return

        }

        Log.d( "MainActivity", "email is " + email)
        Log.d( "MainActivity", "password is  $password")

        //Firebase authentication with email, username, and password

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //else if successful

                    Log.d( "Main", "createdUser with uid: ${it.result.user.uid}")

                }
                .addOnFailureListener {
                    Log.d( "Main", "Failed to create User: ${it.message}")
                    Toast.makeText( this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
                }

    }

}
