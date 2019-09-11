package com.example.tmmarkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        var brandsURL = "https://cmsdeneme2.000webhostapp.com/ecom/fetch_brands.php"

        var brandsList = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this@HomeScreen)

        var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, brandsURL, null, Response.Listener {
            response ->


            for(jsonObject in 0.until(response.length())) {
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }


            var brandsListAdapter = ArrayAdapter(this@HomeScreen, R.layout.brand_item_text_view, brandsList)
            brandsListView.adapter = brandsListAdapter


        }, Response.ErrorListener {
            error ->


            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Yalnyslyk")
            dialog.setMessage("Ulgamda wagtlayynca yalnyslyk bar")
            dialog.create().show()
        })

        requestQ.add(jsonArrayRequest)

        brandsListView.setOnItemClickListener { adapterView, view, i, l ->

            val tappedBrand = brandsList.get(i)

            val intent = Intent(this@HomeScreen, FetchEProductsActivity::class.java)

            intent.putExtra("BRAND", tappedBrand)

            startActivity(intent)


        }

    }
}
