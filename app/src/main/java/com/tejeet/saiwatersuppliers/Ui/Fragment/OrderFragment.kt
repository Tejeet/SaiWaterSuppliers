package com.tejeet.saiwatersuppliers.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.saiwatersuppliers.Constant.ResultData
import com.tejeet.saiwatersuppliers.Data.ModelDTO.AllOrder
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.Ui.Adapters.AllOrdersAdapter
import com.tejeet.saiwatersuppliers.Ui.Adapters.AllOrdersItemClickListener
import com.tejeet.saiwatersuppliers.Ui.Adapters.SocietyAdapter
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivitySovietiesBinding
import com.tejeet.saiwatersuppliers.databinding.FragmentHomeBinding
import com.tejeet.saiwatersuppliers.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment(), AllOrdersItemClickListener {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var allOrdersAdapter: AllOrdersAdapter
    var allOrderList:MutableList<AllOrder> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerview()

        mainViewModel.getAllOrders("1","tejeetm@gmail.com")
            .observe(this, Observer {response->

                when(response){
                    is ResultData.Loading -> {
                        binding.lottieLoading.visibility = View.VISIBLE
                    }
                    is ResultData.Success -> {
                        binding.lottieLoading.visibility = View.GONE

                        response.data?.let {
                            allOrdersAdapter.updateData(it)
                        }
                    }
                    is ResultData.Exception ->{

                    }
                }
            })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerview() {

        allOrdersAdapter = AllOrdersAdapter(allOrderList,this)
        binding.allordersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.allordersRecyclerView.adapter = allOrdersAdapter

    }

    override fun OnOrderCardViewClick(allOrder: AllOrder) {

    }


}