package com.example.tmmarkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMainActivityLogin.setOnClickListener {
            var loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        btnMainActivitySignUp.setOnClickListener {
            var signupIntent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(signupIntent)
        }

    }
}
