package com.example.rania_lngshot.pertemuan_2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.rania_lngshot.R
import java.text.DecimalFormat

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)

        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)
        val tvHasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        val df = DecimalFormat("#.##") // format 2 angka desimal

        // ===== LUAS SEGITIGA =====
        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull()
            val tinggi = etTinggi.text.toString().toDoubleOrNull()

            if (alas == null || tinggi == null) {
                Toast.makeText(this, "Input harus angka!", Toast.LENGTH_SHORT).show()
            } else if (alas <= 0 || tinggi <= 0) {
                Toast.makeText(this, "Nilai harus lebih dari 0!", Toast.LENGTH_SHORT).show()
            } else {
                val hasil = 0.5 * alas * tinggi
                tvHasilSegitiga.text = "Hasil: ${df.format(hasil)}"
            }
        }

        // ===== VOLUME KUBUS =====
        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull()

            if (sisi == null) {
                Toast.makeText(this, "Input harus angka!", Toast.LENGTH_SHORT).show()
            } else if (sisi <= 0) {
                Toast.makeText(this, "Nilai harus lebih dari 0!", Toast.LENGTH_SHORT).show()
            } else {
                val hasil = sisi * sisi * sisi
                tvHasilKubus.text = "Hasil: ${df.format(hasil)}"
            }
        }
    }
}