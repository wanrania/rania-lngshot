package com.example.rania_lngshot.home.pertemuan_10

import ProductAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rania_lngshot.R
import com.example.rania_lngshot.databinding.FragmentTabBBinding

class TabBFragment : Fragment() {

    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel("Pembangunan Jalan Desa", "Progress 75%", "https://picsum.photos/seed/jalan1/400/300"),
        ProductModel("Renovasi Posyandu", "Selesai", "https://picsum.photos/seed/posyandu1/400/300"),
        ProductModel("Pembangunan Balai Desa", "Progress 40%", "https://picsum.photos/seed/balai1/400/300"),
        ProductModel("Perbaikan Drainase", "Progress 90%", "https://picsum.photos/seed/drainase1/400/300"),
        ProductModel("Pembangunan Jembatan Desa", "Progress 60%", "https://picsum.photos/seed/jembatan1/400/300"),

        ProductModel("Pemasangan Lampu Jalan", "Selesai", "https://picsum.photos/seed/lampu1/400/300"),
        ProductModel("Pembangunan Taman Desa", "Progress 50%", "https://picsum.photos/seed/taman1/400/300"),
        ProductModel("Perluasan Area UMKM", "Progress 30%", "https://picsum.photos/seed/umkm1/400/300"),
        ProductModel("Renovasi Mushola Desa", "Selesai", "https://picsum.photos/seed/mushola1/400/300"),
        ProductModel("Pembangunan Sumur Bor", "Progress 85%", "https://picsum.photos/seed/sumur1/400/300"),

        ProductModel("Pembangunan Gedung PAUD", "Progress 70%", "https://picsum.photos/seed/paud1/400/300"),
        ProductModel("Perbaikan Saluran Irigasi", "Progress 55%", "https://picsum.photos/seed/irigasi1/400/300"),
        ProductModel("Pengaspalan Jalan Lingkungan", "Progress 80%", "https://picsum.photos/seed/aspal1/400/300"),
        ProductModel("Pembangunan Pos Keamanan", "Selesai", "https://picsum.photos/seed/poskamling1/400/300"),
        ProductModel("Renovasi Kantor Desa", "Progress 65%", "https://picsum.photos/seed/kantor1/400/300"),

        ProductModel("Pembangunan Tempat Sampah Terpadu", "Progress 45%", "https://picsum.photos/seed/sampah1/400/300"),
        ProductModel("Pembuatan Area Bermain Anak", "Progress 25%", "https://picsum.photos/seed/bermain1/400/300"),
        ProductModel("Pembangunan Lapangan Serbaguna", "Progress 35%", "https://picsum.photos/seed/lapangan1/400/300"),
        ProductModel("Perbaikan Trotoar Desa", "Progress 95%", "https://picsum.photos/seed/trotoar1/400/300"),
        ProductModel("Pembangunan Gudang BUMDes", "Progress 50%", "https://picsum.photos/seed/bumdes1/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTabBBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            /** Mode Grid **/
            layoutManager = GridLayoutManager(requireContext(), 2)

            /** Jika ingin model Linear **/
            //layoutManager = LinearLayoutManager(requireContext())

            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}