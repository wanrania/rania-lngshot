package com.example.rania_lngshot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.rania_lngshot.databinding.ActivityMainBinding
import com.example.rania_lngshot.pertemuan_4.FourthActivity
import com.example.rania_lngshot.pertemuan_4.WebViewActivity
import com.example.rania_lngshot.AuthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Bina Desa"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Petani")

        binding.tvWelcome.text = "Selamat datang, $username!"

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Program Bina Desa PCR")
            intent.putExtra("from", "Desa Binaan")
            intent.putExtra("age", 2024)
            startActivity(intent)
        }

        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout dari aplikasi Bina Desa?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    lifecycleScope.launch {
                        // Delay kecil untuk animasi
                        kotlinx.coroutines.delay(300)
                        val intent = Intent(this@MainActivity, AuthActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    Log.e("Info Dialog", "User logout berhasil!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Logout dibatalkan!")
                }
                .show()
        }
    }
}