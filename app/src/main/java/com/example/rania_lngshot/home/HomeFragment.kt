package com.example.rania_lngshot.home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rania_lngshot.AuthActivity
import com.example.rania_lngshot.MainActivity
import com.example.rania_lngshot.data.api.PhotoApiClient
import com.example.rania_lngshot.databinding.FragmentHomeBinding
import com.example.rania_lngshot.home.pertemuan_10.TenthActivity
import com.example.rania_lngshot.home.pertemuan_2.SecondActivity
import com.example.rania_lngshot.home.pertemuan_4.FourthActivity
import com.example.rania_lngshot.home.pertemuan_7.SeventhActivity
import com.example.rania_lngshot.home.photo.PhotoAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref =
            requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // Pertemuan 2
        binding.btnToSec.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Pertemuan 7
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Main Activity
        binding.btnMain.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Pertemuan 10
        binding.btnTenth.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Filter Chip
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->

            val selectedChipId = checkedIds.firstOrNull()

            if (selectedChipId != null) {

                val chip = group.findViewById<Chip>(selectedChipId)

                when (chip.text.toString()) {

                    "Terbaru" -> {
                        binding.btnToSec.visibility = View.VISIBLE
                        binding.btnToFourth.visibility = View.VISIBLE
                        binding.btnToSeventh.visibility = View.VISIBLE
                        binding.btnTenth.visibility = View.VISIBLE
                    }

                    "Penting" -> {
                        binding.btnToSec.visibility = View.GONE
                        binding.btnToFourth.visibility = View.VISIBLE
                        binding.btnToSeventh.visibility = View.VISIBLE
                        binding.btnTenth.visibility = View.VISIBLE
                    }

                    "Belum Dibaca" -> {
                        binding.btnToSec.visibility = View.VISIBLE
                        binding.btnToFourth.visibility = View.GONE
                        binding.btnToSeventh.visibility = View.GONE
                        binding.btnTenth.visibility = View.VISIBLE
                    }
                }

                Toast.makeText(
                    requireContext(),
                    "Filter: ${chip.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Logout
        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->

                    dialog.dismiss()

                    sharedPref.edit {
                        clear()
                    }

                    startActivity(
                        Intent(
                            requireContext(),
                            AuthActivity::class.java
                        )
                    )

                    requireActivity().finish()

                    Log.d("Logout", "User logout")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Load Data API
        loadPhoto()
    }

    private fun loadPhoto() {

        lifecycleScope.launch {

            try {

                val photos =
                    PhotoApiClient.apiService.getPhotos()

                val adapter = PhotoAdapter(photos)

                binding.rvGallery.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext())
                    this.adapter = adapter
                }

            } catch (e: Exception) {

                Log.e(
                    "API_ERROR",
                    e.message ?: "Unknown Error"
                )

                Toast.makeText(
                    requireContext(),
                    "Gagal memuat data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
