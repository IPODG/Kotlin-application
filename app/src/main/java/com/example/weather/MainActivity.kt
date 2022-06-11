package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)


        val btnStartQuiz: Button = findViewById(R.id.btnStartQuiz)
        btnStartQuiz.setOnClickListener {
            val quizIntent: Intent = Intent(this, Display::class.java)

            startActivity(quizIntent)}


    loadData()


    }

    private fun loadData() {
        val service = ServiceBuilder.buildService(WeatherService::class.java)
        val requestCall = service.getWeather()

            requestCall.enqueue(object : Callback<Weather> {
                override fun onResponse(
//                  When response is done, call API
                    call: Call<Weather>,
                    response: Response<Weather>
                ) {

                    if (response.isSuccessful) {
                        //processes the data
                        val txtName: TextView = findViewById(R.id.txtName)
                        val txtDescription: TextView = findViewById(R.id.txtDescription)
                        val txtTemp: TextView = findViewById(R.id.txtTemp)
                        val imgIcon: ImageView = findViewById(R.id.imgIcon)
                        val weather = response.body()!!

                        txtName.text = weather.name
                        txtTemp.text = weather.main.temp.toString()
                        txtDescription.text = weather.weather[0].description
                        Picasso.get()
//                       Picasso Loads image
                            .load("https://openweathermap.org/img/w/${weather.weather[0].icon}.png")
                            .into(imgIcon)

                    } else {
                        //output alert
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle("API error")
                            .setMessage("Response, but something went wrong${response.message()}")
                            .setPositiveButton(android.R.string.ok) { _, _ -> }
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()

                    }

                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
//              Called if API fails response
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("API error")
                        .setMessage("No response, and something went wrong $t")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()

                }


            })

    }
}