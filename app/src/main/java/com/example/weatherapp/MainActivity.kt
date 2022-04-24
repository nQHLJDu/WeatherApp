package com.example.weatherapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        //時刻の自動更新
        // ハンドラのインスタンス作成
        val hander = Handler()

        timer(name = "Timer", period = 1000) {
            // ハンドラで処理したい内容をメインメソッドに送信
            //timerメソット使用時は別スレットを用意して処理しているため、ハンドラ処理必要
            hander.post {
                val messageView: TextView = findViewById(R.id.time)
                val dtf = SimpleDateFormat("yyyy/MM/dd HH:mm")
                val result = dtf.format(Date())
                messageView.text = result
            }
        }
    }


    val apiKey = ""
    val mainUrl = "https://api.openweathermap.org/data/2.5/weather?lang=ja"
    companion object{
       const val UrlData = "com.example.testactivitytrasdata.DATA"
    }


    fun btn_hokkaido(view: View) {
        val weatherUrl = "$mainUrl&q=hokkaido&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_niigata(view: View) {
        val weatherUrl = "$mainUrl&q=niigata-ken&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_tokyo(view: View) {
        val weatherUrl = "$mainUrl&q=tokyo&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_ishikawa(view: View) {
        val weatherUrl = "$mainUrl&q=kanazawa&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_aichi(view: View) {
        val weatherUrl = "$mainUrl&q=nagoya&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_osaka(view: View) {
        val weatherUrl = "$mainUrl&q=osaka-fu&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_kochi(view: View) {
        val weatherUrl = "$mainUrl&q=KOCHI%20Prefecture&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_kagoshima(view: View) {
        val weatherUrl = "$mainUrl&q=kagoshima-ken&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_okinawa(view: View) {
        val weatherUrl = "$mainUrl&q=naha&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_fukuoka(view: View) {
        val weatherUrl = "$mainUrl&q=fukuoka-ken&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_hiroshima(view: View) {
        val weatherUrl = "$mainUrl&q=hiroshima-ken&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }

    fun btn_miyagi(view: View) {
        val weatherUrl = "$mainUrl&q=sendai&appid=$apiKey"
        val intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(UrlData,weatherUrl)
        startActivity(intent)
    }
}