package com.example.tmmarkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUpSignUp.setOnClickListener {
            if(edtSignUpPhone.text.length==8){

                var registerUserURL = "https://cmsdeneme2.000webhostapp.com/ecom/join_new_user.php?email=" +
                        edtSignUpEmail.text.toString() +"&username="+edtSignUpName.text.toString() +
                        "&password="+edtSignUpPassword.text.toString()+"&phone="+edtSignUpPhone.text.toString()

                val requestQ = Volley.newRequestQueue(this@SignUpActivity)
                val stringRequest = StringRequest(Request.Method.GET, registerUserURL, Response.Listener 
                {response ->  
                    if(response.equals("Bu nomer baska ulanyjy ucin registratsiya edilen")){
                        var dialog = AlertDialog.Builder(this)
                        dialog.setTitle("Bellik")
                        dialog.setMessage(response)
                        dialog.create().show()
                    }else{



//                        var dialog = AlertDialog.Builder(this)
//                        dialog.setTitle("Bellik")
//                        dialog.setMessage(response)
//                        dialog.create().show()

                        Person.phone = edtSignUpPhone.text.toString()
                        Person.username = edtSignUpName.text.toString()

                        Toast.makeText(this@SignUpActivity, response, Toast.LENGTH_SHORT).show()

                        val homeIntent = Intent(this@SignUpActivity, HomeScreen:: class.java)
                        startActivity(homeIntent)

                    }
                }, Response.ErrorListener 
                {error ->
                    var dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Yalnyshlyk")
                    dialog.setMessage("Ulgamda wagtlayynca yalnyshlyk boldy" + error.message)
                    dialog.create().show()
                })

                requestQ.add(stringRequest)

            }else{
                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("Bellik")
                dialog.setMessage("Telefon Belginizi 8 sifr edip yazyn!(65123456)")
                dialog.create().show()
            }


        }//btnSignUpSignup

        btnSigUpLogin.setOnClickListener {
            var loginIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }//onCreate
}//activity
