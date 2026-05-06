package com.example.rania_lngshot

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.rania_lngshot.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Spinner Agama
        val agamaList = arrayOf("Islam", "Kristen", "Hindu", "Budha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agamaList)
        binding.spAgama.adapter = adapter

        // DatePicker
        binding.etTanggal.setOnClickListener {
            val calendar = Calendar.getInstance()

            val datePicker = DatePickerDialog(
                this,
                { _, year, month, day ->
                    val tanggal = "$day/${month + 1}/$year"
                    binding.etTanggal.setText(tanggal)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Submit
        binding.btnSubmit.setOnClickListener {

            val nama = binding.etNama.text.toString().trim()
            val tanggal = binding.etTanggal.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirm = binding.etConfirmPassword.text.toString().trim()

            val genderId = binding.rgGender.checkedRadioButtonId
            val agama = binding.spAgama.selectedItem.toString()

            var isValid = true

            // RESET ERROR
            binding.etNama.error = null
            binding.etTanggal.error = null
            binding.etUsername.error = null
            binding.etPassword.error = null
            binding.etConfirmPassword.error = null

            // VALIDASI
            if (nama.isEmpty()) {
                binding.etNama.error = "Nama wajib diisi"
                isValid = false
            }

            if (tanggal.isEmpty()) {
                binding.etTanggal.error = "Tanggal lahir wajib diisi"
                isValid = false
            }

            if (genderId == -1) {
                binding.rbLaki.error = "Pilih jenis kelamin"
                isValid = false
            }

            if (username.isEmpty()) {
                binding.etUsername.error = "Username wajib diisi"
                isValid = false
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password wajib diisi"
                isValid = false
            }

            if (confirm.isEmpty()) {
                binding.etConfirmPassword.error = "Konfirmasi password"
                isValid = false
            }

            if (password != confirm) {
                binding.etConfirmPassword.error = "Password tidak sama"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            // Ambil gender
            val gender = when (genderId) {
                R.id.rbLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> ""
            }

            // SIMPAN KE SharedPreferences
            val editor = sharedPref.edit()
            editor.putString("nama", nama)
            editor.putString("tanggal", tanggal)
            editor.putString("gender", gender)
            editor.putString("agama", agama)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            // Clear form
            binding.etNama.text?.clear()
            binding.etTanggal.text?.clear()
            binding.etUsername.text?.clear()
            binding.etPassword.text?.clear()
            binding.etConfirmPassword.text?.clear()
            binding.rgGender.clearCheck()

            finish()
        }
    }
}