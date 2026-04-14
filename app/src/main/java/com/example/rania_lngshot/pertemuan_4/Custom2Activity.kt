package com.example.rania_lngshot.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R

class Custom2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom2)

        Log.e("onCreate", "Custom2Activity dibuat")

        // INSETS
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // AMBIL DATA DARI INTENT
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")

        // HUBUNGKAN VIEW
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDesc = findViewById<TextView>(R.id.tvDesc)
        val btnDialog = findViewById<Button>(R.id.btnDialog)

        tvTitle.text = title
        tvDesc.text = desc

        // ALERT DIALOG
        btnDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah ingin melanjutkan?")
                .setPositiveButton("Ya") { _, _ ->
                    Log.e("Dialog", "User pilih Ya")
                }
                .setNegativeButton("Batal") { _, _ ->
                    Log.e("Dialog", "User pilih Batal")
                }
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "Custom2Activity tampil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Custom2Activity dihancurkan")
    }
}