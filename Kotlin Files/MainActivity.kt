package com.example.rajatmonga.kotlinchatapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        register_button_register.setOnClickListener {

            val email = email_edit_text_register.text.toString()
            val password = password_edit_text_register.text.toString()


            Log.d( "MainActivity", "email is " + email)
            Log.d( "MainActivity", "password is  $password")

            //Firebase authentication with email, username, and password


        }


        already_have_account_text_view.setOnClickListener {
            Log.d("MainActivity", "try to show login activity")

            // launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}
