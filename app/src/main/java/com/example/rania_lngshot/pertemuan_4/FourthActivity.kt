package com.example.rania_lngshot.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rania_lngshot.MainActivity
import com.example.rania_lngshot.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Data Desa Binaan"

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val name = intent.getStringExtra("name") ?: "Program Bina Desa"
        val from = intent.getStringExtra("from") ?: "Desa Binaan"
        val age = intent.getIntExtra("age", 0)

        binding.tvData.text = """
            Nama Program: $name
            Lokasi: $from
            Tahun: $age
        """.trimIndent()

        Log.e("Data Intent", "Nama: $name, Tahun: $age, Lokasi: $from")

        binding.btnKembali.setOnClickListener {
            finish()
        }

        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Data desa berhasil disimpan!", Snackbar.LENGTH_LONG)
                .setAction("OK") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin menyimpan data desa binaan?")
                .setPositiveButton("Simpan") { dialog, _ ->
                    Snackbar.make(binding.root, "Data berhasil disimpan!", Snackbar.LENGTH_SHORT)
                        .setAction("Tutup") {
                            Log.e("Info Snackbar", "Snackbar ditutup")
                            dialog.dismiss()
                        }
                        .show()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Dibatalkan!")
                }
                .show()
        }
    }
}