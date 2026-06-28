package com.example.rania_lngshot.home.agenda_desa

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rania_lngshot.R
import com.example.rania_lngshot.databinding.ActivityAgendaDesaBinding

class AgendaDesaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaDesaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaDesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Halaman Agenda"
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val data = listOf(

            AgendaModel(
                "Gotong Royong",
                "28 Juni 2026",
                "21.30",
                "Balai Desa",
                "Membersihkan lingkungan desa."
            ),

            AgendaModel(
                "Posyandu",
                "30 Juni 2026",
                "09.00",
                "Posyandu Mawar",
                "Pemeriksaan balita."
            ),

            AgendaModel(
                "Musyawarah Desa",
                "2 Juli 2026",
                "13.30",
                "Balai Desa",
                "Pembahasan program kerja."
            )

        )

        binding.rvAgenda.layoutManager = LinearLayoutManager(this)

        binding.rvAgenda.adapter = AgendaAdapter(data)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}