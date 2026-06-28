package com.example.rania_lngshot.home.agenda_desa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R
import com.example.rania_lngshot.databinding.ActivityAgendaDetailBinding
import android.Manifest
import android.os.Build
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.rania_lngshot.utils.PermissionHelper
import com.example.rania_lngshot.utils.ReminderHelper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AgendaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaDetailBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {
                Toast.makeText(
                    this,
                    "Izin notifikasi diberikan",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Izin notifikasi ditolak",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Halaman Detail Agenda"
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val location = intent.getStringExtra("location")
        val description = intent.getStringExtra("description")

        binding.tvTitle.text = title

        binding.tvDate.text = "Tanggal : $date"

        binding.tvTime.text = "Jam : $time"

        binding.tvLocation.text = "Lokasi : $location"

        binding.tvDescription.text = description

        if (
            PermissionHelper.isNotificationPermissionRequired() &&
            !PermissionHelper.hasPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            )
        ) {

            PermissionHelper.requestPermission(
                notificationPermissionLauncher,
                Manifest.permission.POST_NOTIFICATIONS
            )

        }

        binding.btnReminder.setOnClickListener {

            val reminderMinute = when {

                binding.rb5.isChecked -> 5
                binding.rb10.isChecked -> 10
                binding.rb30.isChecked -> 30
                binding.rb60.isChecked -> 60

                else -> {
                    Toast.makeText(
                        this,
                        "Silakan pilih waktu reminder",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }

            // Format tanggal dan jam
            val format = SimpleDateFormat(
                "dd MMMM yyyy HH.mm",
                Locale("id", "ID")
            )

            val calendar = Calendar.getInstance()

            val agendaDate = "$date $time"

            val parsedDate = format.parse(agendaDate)

            if (parsedDate != null) {

                calendar.time = parsedDate

                // Kurangi sesuai pilihan reminder
                calendar.add(Calendar.MINUTE, -reminderMinute)

                ReminderHelper.setReminder(

                    context = this,

                    hour = calendar.get(Calendar.HOUR_OF_DAY),

                    minute = calendar.get(Calendar.MINUTE),

                    title = "Agenda Desa",

                    message = "$title akan dimulai dalam $reminderMinute menit.",

                    targetActivity = AgendaDesaActivity::class.java

                )

                Toast.makeText(
                    this,
                    "Reminder berhasil disimpan",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                Toast.makeText(
                    this,
                    "Format tanggal atau jam tidak valid",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

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