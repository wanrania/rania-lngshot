package com.example.rania_lngshot

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets untuk edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Cek apakah sudah login (opsional - bisa di-comment jika ingin selalu tampil login)
        /*
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        */

        setupClickListeners(sharedPref)
    }

    private fun setupClickListeners(sharedPref: android.content.SharedPreferences) {
        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

            val savedUser = sharedPref.getString("username", "")
            val savedPass = sharedPref.getString("password", "")

            // VALIDASI KOSONG
            if (username.isEmpty()) {
                binding.etUsername.error = "Username wajib diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }

            // 🔥 RULE 1: username == password
            val rule1 = username == password

            // 🔥 RULE 2: sesuai SharedPreferences
            val rule2 = username == savedUser && password == savedPass

            if (rule1 || rule2) {

                // simpan status login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                binding.etPassword.error = "Username atau password salah"
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Login Gagal")
            .setMessage(message)
            .setPositiveButton("Coba Lagi") { dialog, _ ->
                dialog.dismiss()
                // Clear input fields
                binding.etUsername.text?.clear()
                binding.etPassword.text?.clear()
                binding.etUsername.requestFocus()
            }
            .setCancelable(false)
            .show()
    }

    override fun onBackPressed() {
        // Disable back button di AuthActivity
        MaterialAlertDialogBuilder(this)
            .setTitle("Keluar Aplikasi")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi Bina Desa?")
            .setPositiveButton("Keluar") { _, _ ->
                finishAffinity()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}