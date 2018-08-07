package com.example.rajatmonga.kotlinchatapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.rajatmonga.kotlinchatapp.R.id.login_button_login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activitiy_login.*

/**
 * Created by rajatmonga on 7/14/18.
 */

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d ( "Login", "Attempt to login with email & pw: $email")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            //.addOnCompleteListener()
        }

    }

    override fun setContentView(view: View?) {
        super.setContentView(R.layout.activitiy_login)
    }
}
