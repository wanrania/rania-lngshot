package com.example.rania_lngshot.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R
import com.google.android.material.snackbar.Snackbar

class Custom1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom1)

        Log.e("onCreate", "Custom1Activity dibuat")

        // INSETS
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // AMBIL DATA DARI INTENT
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")

        // HUBUNGKAN KE VIEW
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDesc = findViewById<TextView>(R.id.tvDesc)
        val btnSnackbar = findViewById<Button>(R.id.btnSnackbar)

        tvTitle.text = title
        tvDesc.text = desc

        // SNACKBAR
        btnSnackbar.setOnClickListener {
            Snackbar.make(it, "Ini adalah Custom 1", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") {
                    Log.e("Snackbar", "Ditutup")
                }
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "Custom1Activity tampil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Custom1Activity dihancurkan")
    }
}