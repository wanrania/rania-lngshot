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
import com.example.rania_lngshot.AuthActivity
import com.example.rania_lngshot.MainActivity
import com.example.rania_lngshot.databinding.FragmentHomeBinding
import com.example.rania_lngshot.home.pertemuan_10.TenthActivity
import com.example.rania_lngshot.home.pertemuan_2.SecondActivity
import com.example.rania_lngshot.home.pertemuan_4.FourthActivity
import com.example.rania_lngshot.home.pertemuan_7.SeventhActivity
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnToSec.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }
        
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnMain.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnTenth.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

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

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()

                    sharedPref.edit {
                        clear()
                    }

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
