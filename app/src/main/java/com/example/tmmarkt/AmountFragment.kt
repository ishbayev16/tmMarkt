package com.example.tmmarkt


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlin.math.acos


class AmountFragment : android.app.DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddToCart = fragmentView.findViewById<Button>(R.id.btnAddCart)

        btnAddToCart.setOnClickListener {
            var ptoURL = "https://cmsdeneme2.000webhostapp.com/ecom/insert_temporary_order.php?phone=${Person.phone.toInt()}" +
                    "&product_id=${Person.addToCartProductID}&amount=${edtEnterAmount.text}"

            var requestQ = Volley.newRequestQueue(activity)

            var stringRequest = StringRequest(Request.Method.GET, ptoURL, Response.Listener {
                response ->

                var intent = Intent(activity, CartProductsActivity::class.java)
                startActivity(intent)

            }, Response.ErrorListener{
                error->
            })

            requestQ.add(stringRequest)
        }

        return fragmentView
    }


}
