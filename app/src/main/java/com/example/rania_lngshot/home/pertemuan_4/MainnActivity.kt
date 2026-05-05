package com.example.rania_lngshot.home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R
import com.google.android.material.snackbar.Snackbar

class MainnActivity : AppCompatActivity() {

    private val titleText = "Aplikasi Bangun Ruang"
    private val descText = "Pilih menu di bawah"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainn)

        Log.e("onCreate", "MainnActivity dibuat")

        // INSETS (BIAR GA KETUTUP STATUS BAR)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // INISIALISASI BUTTON
        val btnRumus = findViewById<Button>(R.id.btnRumus)
        val btnCustom1 = findViewById<Button>(R.id.btnCustom1)
        val btnCustom2 = findViewById<Button>(R.id.btnCustom2)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // TOMBOL 1 → RUMUS
        btnRumus.setOnClickListener {
            val intent = Intent(this, RumusActivity::class.java)
            intent.putExtra("title", titleText)
            intent.putExtra("desc", descText)
            intent.putExtra("age", 20)
            startActivity(intent)
        }

        // TOMBOL 2 → CUSTOM 1
        btnCustom1.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("title", titleText)
            intent.putExtra("desc", descText)
            startActivity(intent)
        }

        // TOMBOL 3 → CUSTOM 2
        btnCustom2.setOnClickListener {
            val intent = Intent(this, Custom2Activity::class.java)
            intent.putExtra("title", titleText)
            intent.putExtra("desc", descText)
            startActivity(intent)
        }

        // TOMBOL 4 → LOGOUT
        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Logout dibatalkan",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "MainnActivity tampil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "MainnActivity dihancurkan")
    }
}