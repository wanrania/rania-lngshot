package com.example.rania_lngshot.home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginn)

        Log.e("onCreate", "LoginActivity dibuat")

        // INSETS
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // INISIALISASI VIEW
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // PINDAH KE MAIN
                startActivity(Intent(this, MainnActivity::class.java))
                finish()
            } else {
                // VALIDASI ERROR
                if (username.isEmpty()) {
                    etUsername.error = "Username wajib diisi"
                }
                if (password.isEmpty()) {
                    etPassword.error = "Password wajib diisi"
                }

                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Harap isi semua field",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "LoginActivity tampil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "LoginActivity dihancurkan")
    }
}