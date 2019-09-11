package com.example.tmmarkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*

class CartProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

        var cartProductsURL = "https://cmsdeneme2.000webhostapp.com/ecom/fetch_temporary_order.php?phone=${Person.phone}"

        var cartProductsList = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this)
        var jsonAR = JsonArrayRequest(Request.Method.GET, cartProductsURL, null, Response.Listener {
            response ->

            for(joIndex in 0.until(response.length())){
                cartProductsList.add("${response.getJSONObject(joIndex).getInt("id")}\n ${response.getJSONObject(joIndex).getString("name")} \n ${response.getJSONObject(
                    joIndex).getInt("price")}\n ${response.getJSONObject(joIndex).getInt("phone")}\n ${response.getJSONObject(
                    joIndex).getInt("amount")}\n")
            }

            var cartProductsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,cartProductsList)

            cartProductsListView.adapter = cartProductsAdapter

        }, Response.ErrorListener {
            error ->
        })

        requestQ.add(jsonAR)
    }//oncreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item?.itemId == R.id.continueShoppingItem || item?.itemId == R.id.continueShoppingIcon){

            var intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)

        } else if (item?.itemId == R.id.declineOrderItem || item?.itemId == R.id.declineOrderIcon){
            var declineURL = "https://cmsdeneme2.000webhostapp.com/ecom/decline_order.php?phone=${Person.phone}"

            var requestQ = Volley.newRequestQueue(this)
            var stringRequest = StringRequest(Request.Method.GET, declineURL, Response.Listener { 
                response ->

                var intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)

            }, Response.ErrorListener { 
                error ->

                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("Yalnyshlyk")
                dialog.setMessage("Ulgamda wagtlayynca yalnyshlyk boldy" + error.message)
                dialog.create().show()
                
            })

            requestQ.add(stringRequest)
        }


        return super.onOptionsItemSelected(item)
    }

}
