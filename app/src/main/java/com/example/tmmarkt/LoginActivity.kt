package com.example.tmmarkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginSignUp.setOnClickListener {
            var signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        btnLoginMainPage.setOnClickListener {
            val homeIntent = Intent(this@LoginActivity, HomeScreen:: class.java)
            startActivity(homeIntent)
        }

        btnLoginLogin.setOnClickListener {
            val loginUserURL = "https://cmsdeneme2.000webhostapp.com/ecom/login_app_user.php?phone="+edtLoginPhone.text.toString()+
            "&password="+edtLoginPassword.text.toString()

            val requestQ = Volley.newRequestQueue(this@LoginActivity)
            val stringRequest = StringRequest(Request.Method.GET, loginUserURL, Response.Listener { 
                response ->

                if(response.equals("hos geldiniz")) {

                    Person.phone = edtLoginPhone.text.toString()

                    Toast.makeText(this@LoginActivity, "Hosgeldiniz", Toast.LENGTH_SHORT).show()

                    val homeIntent = Intent(this@LoginActivity, HomeScreen:: class.java)
                    startActivity(homeIntent)



                    } else {

                    var dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Yalnyshlyk bar")
                    dialog.setMessage(response)
                    dialog.create().show()
                }

            }, Response.ErrorListener { 
                error ->

                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("Yalnyshlyk")
                dialog.setMessage("Ulgamda wagtlayynca yalnyshlyk boldy" + error.message)
                dialog.create().show()
            })

            requestQ.add(stringRequest)
        }

    }
}
