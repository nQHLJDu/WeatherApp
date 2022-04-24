package com.example.weatherapp



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_screen)


        val tvCityName: TextView = findViewById(R.id.tv_CityName)
        val tvCityWeather: TextView = findViewById(R.id.tv_CityWeather)
        val tvMax: TextView = findViewById(R.id.tv_max)
        val tvMin: TextView = findViewById(R.id.tv_min)
        val tvHumidity: TextView = findViewById(R.id.tv_humidity)
        val btnClear: Button = findViewById(R.id.btn_clear)


        //mainActivityからデータを取得
        val intent: Intent = getIntent()
        val weatherUrl: String? = intent.getStringExtra(MainActivity.UrlData)
        if (weatherUrl != null) {
            weatherTask(weatherUrl)
        }
    }

        fun weatherTask(Url:String) {
            //コルーチンスコープの用意
            lifecycleScope.launch {
                val result = weatherBackgroundTask(Url)
                weatherJsonTask(result)
            }
        }


        //外部アクセス
        suspend fun weatherBackgroundTask(weatherUrl: String): String {
            //ワーカースレッド(IO)での実行
            val response = withContext(Dispatchers.IO) {
                var httpResult = ""

                try {
                    val urlObj = URL(weatherUrl)
                    val br = BufferedReader(InputStreamReader(urlObj.openStream()))
                    httpResult = br.readText()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                return@withContext httpResult
            }

            return response
        }


        fun weatherJsonTask(result: String) {

            val tvCityName: TextView = findViewById(R.id.tv_CityName)
            val tvCityWeather: TextView = findViewById(R.id.tv_CityWeather)
            val tvMax: TextView = findViewById(R.id.tv_max)
            val tvMin: TextView = findViewById(R.id.tv_min)
            val tvHumidity: TextView = findViewById(R.id.tv_humidity)

            //都市名
            val jsonObj = JSONObject(result)
            val cityName = jsonObj.getString("name")
            tvCityName.text = cityName

            //天気
            val weatherJSONArray = jsonObj.getJSONArray("weather")
            val weatherJSON = weatherJSONArray.getJSONObject(0)
            val weather = weatherJSON.getString("description")
            tvCityWeather.text = weather
                if("晴れ" in weather) {
                    val imageView1: ImageView = findViewById(R.id.image_view1)
                    imageView1.setImageResource(R.drawable.img_sunny)
                } else if("雷雨" in weather){
                    val imageView1: ImageView = findViewById(R.id.image_view1)
                    imageView1.setImageResource(R.drawable.img_lightning)
                } else if("雨" in weather) {
                    val imageView1: ImageView = findViewById(R.id.image_view1)
                    imageView1.setImageResource(R.drawable.img_rainy)
                } else if("雲" in weather ) {
                    val imageView1: ImageView = findViewById(R.id.image_view1)
                    imageView1.setImageResource(R.drawable.img_cloud)
                } else if("曇" in weather ){
                    val imageView1: ImageView = findViewById(R.id.image_view1)
                    imageView1.setImageResource(R.drawable.img_cloud)
                } else if("雪" in weather){
                    val imageView1: ImageView = findViewById(R.id.image_view1)
                    imageView1.setImageResource(R.drawable.img_snow)
                }


            //気温
            val main = jsonObj.getJSONObject("main")
            tvMax.text = "最高気温${main.getInt("temp_max")-270}℃"
            tvMin.text = "最低気温${main.getInt("temp_min")-270}℃"

            //湿度
            val humidity = jsonObj.getJSONObject("main")
            tvHumidity.text = "湿度${humidity.getInt("humidity")}%"
            }



    fun btn_clear(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

}





