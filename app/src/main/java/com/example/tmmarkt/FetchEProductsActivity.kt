package com.example.tmmarkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*

class FetchEProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val selectedBrand:String = intent.getStringExtra("BRAND")

        txtBrandName.text = "$selectedBrand markasynyn harytlary"

        val productsURL = "https://cmsdeneme2.000webhostapp.com/ecom/fetch_products.php?brand=$selectedBrand"

        var productsList = ArrayList<EProduct>()

        val requestQ = Volley.newRequestQueue(this)

        val jsonAR = JsonArrayRequest(Request.Method.GET, productsURL, null, Response.Listener { 
            response ->


            for(productJOIndex in 0.until(response.length())){

                productsList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                    response.getJSONObject(productJOIndex).getString("name"),
                    response.getJSONObject(productJOIndex).getInt("price"),
                    response.getJSONObject(productJOIndex).getString("brand"),
                    response.getJSONObject(productJOIndex).getString("picture")))


            }

            val pAdapter = EProductAdapter(this, productsList)
            productsRV.layoutManager = LinearLayoutManager(this)
            productsRV.adapter = pAdapter


        }, Response.ErrorListener { 
            error ->

            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Yalnyslyk")
            dialog.setMessage("Ulgamda wagtlayynca yalnyslyk bar")
            dialog.create().show()
        })


        requestQ.add(jsonAR)

    }
}
