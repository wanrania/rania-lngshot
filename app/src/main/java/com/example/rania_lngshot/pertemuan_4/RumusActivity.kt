package com.example.rania_lngshot.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R

class RumusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rumus)

        Log.e("onCreate", "RumusActivity dibuat")

        // INSETS (BIAR AMAN DARI STATUS BAR)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // AMBIL DATA DARI INTENT
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")
        val age = intent.getIntExtra("age", 0)

        Log.e("Intent", "Title: $title, Desc: $desc, Age: $age")

        // TAMPILKAN KE TEXTVIEW
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDesc = findViewById<TextView>(R.id.tvDesc)
        val tvRumus = findViewById<TextView>(R.id.tvRumus)

        tvTitle.text = title
        tvDesc.text = desc
        tvRumus.text = "Contoh Rumus:\nVolume Kubus = s × s × s"
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "RumusActivity tampil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "RumusActivity dihancurkan")
    }
}