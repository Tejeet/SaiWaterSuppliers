package com.tejeet.saiwatersuppliers.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tejeet.saiwatersuppliers.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    var toggleState:Boolean= true

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnToggle.setOnClickListener {
            if (toggleState){
                binding.idSalesLayout.visibility = View.GONE
                toggleState = false
            }else{
                binding.idSalesLayout.visibility = View.VISIBLE
                toggleState = true
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}