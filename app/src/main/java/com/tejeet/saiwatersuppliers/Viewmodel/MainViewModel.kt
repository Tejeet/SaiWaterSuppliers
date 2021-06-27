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
import com.tejeet.saiwatersuppliers.Data.ModelDTO.*
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
        password: String,
        adminName: String,
    ): Response<AddUserResponseDTO> {

         val response = CoroutineScope(Dispatchers.IO).async {
                 return@async mainRepository.addUser(societyName,customerName,
                     customerEmail,customerMobile,customerAddress,tankerRate,password,adminName)

         }
         return response.await()


    }

    suspend fun userLogin(userId:String, userPass:String, firebaseToken:String, smartphone:String,smartphoneModel:String, apiLevel:String,androidversion:String): Response<LoginResponseDTO> {

        val response = CoroutineScope(Dispatchers.IO).async {
            return@async mainRepository.userLogin(userId,userPass, firebaseToken, smartphone, smartphoneModel, apiLevel, androidversion)
        }
        return response.await()

    }


    suspend fun userFBTokenUpdate(userEmail:String, userID:String, firebaseToken:String): Response<UserFBTokenUpdateResponseDTO> {

        val response = CoroutineScope(Dispatchers.IO).async {
            return@async mainRepository.userFirebaseTokenUpdate(userEmail,userID, firebaseToken)
        }
        return response.await()

    }


    suspend fun addDriver(driverName: String, driverEmail:String, driverMobile:String, driverPass:String): Response<AddDriverResponseDTO> {

        val response = CoroutineScope(Dispatchers.IO).async {
            return@async mainRepository.addDriver(driverName,driverEmail, driverMobile,driverPass)
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


    fun getAllDrivers(userId:String,userEmail:String): LiveData<ResultData<MutableList<TankerDriver>?>> {


        return flow {
            emit(ResultData.Loading())
            if (hasInternetConnection()){
                emit(ResultData.Success(mainRepository.getAllDrivers(userId,userEmail)))
            }else{
                emit(ResultData.Exception("NO Internet Connection"))
            }


        }.asLiveData(Dispatchers.IO)

    }


    fun getAllOrders(userId:String,userEmail:String): LiveData<ResultData<MutableList<AllOrder>?>> {


        return flow {
            emit(ResultData.Loading())
            if (hasInternetConnection()){
                emit(ResultData.Success(mainRepository.getAllOrders(userId,userEmail)))
            }else{
                emit(ResultData.Exception("NO Internet Connection"))
            }


        }.asLiveData(Dispatchers.IO)

    }

    fun getDetailedOrderReport(customerID:String,userId:String,userEmail:String): LiveData<ResultData<MutableList<AllDetailedOrder>?>> {


        return flow {
            emit(ResultData.Loading())
            if (hasInternetConnection()){
                emit(ResultData.Success(mainRepository.getDetailedOrderDetails(customerID,userId,userEmail)))
            }else{
                emit(ResultData.Exception("NO Internet Connection"))
            }


        }.asLiveData(Dispatchers.IO)

    }

    fun getAllRevenue(userId:String,userEmail:String): LiveData<ResultData<MutableList<RevenueDetail>?>> {


        return flow {
            emit(ResultData.Loading())
            if (hasInternetConnection()){
                emit(ResultData.Success(mainRepository.getAllRevenue(userId,userEmail)))
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