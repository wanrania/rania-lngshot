package com.example.rania_lngshot.note

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rania_lngshot.R
import com.example.rania_lngshot.data.AppDatabase
import com.example.rania_lngshot.data.entity.NoteEntity
import com.example.rania_lngshot.databinding.FragmentHomeBinding
import com.example.rania_lngshot.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {
    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** Inisialisasi AppDatabase & Adapter **/
        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notes, this) // --> tambah parameter this

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        /** Tambah ini sebagai garis pemisah **/
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        fetchNotes()

        binding.toolbar.title = "Catatan"
        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }

    }

    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.noteDao().getAll() //pemanggilan query
            notes.clear()
            notes.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note) //Hapus Note
            fetchNotes()              //Fetch lagi data notes terbaru
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}