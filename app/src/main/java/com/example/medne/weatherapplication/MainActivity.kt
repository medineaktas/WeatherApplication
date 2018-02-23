package com.example.medne.weatherapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

   var spinnerAdepter= ArrayAdapter.createFromResource(this,R.array.citys,R.layout.spinner_item)
    spinnerAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spnCitys.setTitle("Şehir Secin")
        spnCitys.setPositiveButton("SEÇ")
        spnCitys.adapter=spinnerAdepter
        spnCitys.setOnItemSelectedListener(this)


        dateFrom("Ankara" )
    }










    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
     var selectedCity=p0?.getItemAtPosition(p2).toString()
        dateFrom(selectedCity)

    }

    fun dateWrite():String{
        var calendar=Calendar.getInstance().time
        var format=SimpleDateFormat("EEEE MMMM yyyy", Locale("tr"))
        var date=format.format(calendar)

        return date
    }

    fun dateFrom(city:String){
    var url= "http://api.openweathermap.org/data/2.5/weather?q=$city,tr&appid=abbcd2fcfec741ec783669c98b7f39d1&lang=tr&units=metric"

    var  weatherObjectRequest= JsonObjectRequest(Request.Method.GET,url,null,object : Response.Listener<JSONObject> {
        override fun onResponse(response: JSONObject?) {

            var main=response?.getJSONObject("main")
            var temp=main?.getInt("temp")
            twTemp.text=temp.toString()


            var weather=response?.getJSONArray("weather")
            var explanation=weather?.getJSONObject(0)?.getString("description")
            twExpl.text=explanation

            twHisory.text= dateWrite()



        }
    },object: Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
        }


    })
    MySingleton.getInstance(this).addToRequestQuene(weatherObjectRequest)



}

}