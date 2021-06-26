package com.tejeet.saiwatersuppliers.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.saiwatersuppliers.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding = FragmentProfileBinding.inflate(inflater, container, false)

        AppPreferences.init(requireContext())

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    fun init(){
        binding.idafUserName.text = AppPreferences.userName.toString()
        binding.idafUserEmail.text = AppPreferences.userEmail.toString()
        binding.idafuserMobile.text = AppPreferences.userMobile.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}