package com.tejeet.saiwatersuppliers.Viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.tejeet.saiwatersuppliers.Constant.ConstantsData
import com.tejeet.saiwatersuppliers.Constant.ResultData
import com.tejeet.saiwatersuppliers.Data.ModelDTO.AddUserResponseDTO
import com.tejeet.saiwatersuppliers.Data.ModelDTO.GetAllUserDTO
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.Repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    application: Application
): AndroidViewModel(application) {


     suspend fun addUser(
        societyName:String,
        customerName:String,
        customerEmail:String,
        customerMobile: String,
        customerAddress: String,
        tankerRate: String,
        adminName: String,
    ): Response<AddUserResponseDTO> {

         val response = CoroutineScope(Dispatchers.IO).async {
                 return@async mainRepository.addUser(societyName,customerName,
                     customerEmail,customerMobile,customerAddress,tankerRate,adminName)

         }
         return response.await()


    }

    fun getAllUser(userId:String,userEmail:String): LiveData<ResultData<MutableList<MyCustomer>?>> {


        return flow {
            emit(ResultData.Loading())
            if (hasInternetConnection()){
                emit(ResultData.Success(mainRepository.getAllUser(userId,userEmail)))
            }else{
                emit(ResultData.Exception("NO Internet Connection"))
            }


        }.asLiveData(Dispatchers.IO)

    }

    fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        )as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false;
        }
    }
}