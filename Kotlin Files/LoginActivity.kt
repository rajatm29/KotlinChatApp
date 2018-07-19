package com.example.rajatmonga.kotlinchatapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by rajatmonga on 7/14/18.
 */

class LoginActivity: AppCompatActivity() {
    
    super.onCreate(savedInstanceState)
    
    setContentView(R.layout.activity_login)
    
    login_button_login.setOnClickListener {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()
        
        Log.d ( "Login", "Attempt to login with email & pw: $email")
        
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            //.addOnCompleteListener()
            
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_login)
    }

}
