package com.example.rania_lngshot.note

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.rania_lngshot.R
import com.example.rania_lngshot.data.AppDatabase
import com.example.rania_lngshot.data.entity.NoteEntity
import com.example.rania_lngshot.databinding.ActivityNoteFormBinding
import com.example.rania_lngshot.databinding.ActivityTenthBinding
import com.example.rania_lngshot.home.pertemuan_10.TenthTabsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase //Tambahkan baris ini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Note Form"
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        /** Inisialisasi DB **/
        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                /** Penggunaan Coroutine dalam melakukan insert data **/
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.noteDao().insert(note)
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
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