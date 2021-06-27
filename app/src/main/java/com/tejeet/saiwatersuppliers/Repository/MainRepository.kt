package com.tejeet.saiwatersuppliers.Repository

import com.tejeet.saiwatersuppliers.Constant.ConstantsData.API_KEY
import com.tejeet.saiwatersuppliers.Data.ModelDTO.*
import com.tejeet.saiwatersuppliers.Network.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(
   private val apiService: ApiService
){

   suspend fun addUser( societyName:String,  customerName:String, customerEmail:String, customerMobile: String, customerAddress: String, tankerRate: String, password:String, adminName: String ): Response<AddUserResponseDTO> {

      return  apiService.addUser("OK",API_KEY,societyName,customerName, customerEmail,customerMobile,customerAddress,tankerRate,password,adminName)
   }

   suspend fun getAllUser(userId:String,userEmail:String):MutableList<MyCustomer>{
      return apiService.getAllUser("Ok", API_KEY,userId,userEmail).body()!!.myCustomers as MutableList<MyCustomer>
   }

   suspend fun getAllDrivers(userId:String,userEmail:String):MutableList<TankerDriver>{
      return apiService.getAllDrivers("Ok", API_KEY,userId,userEmail).body()!!.tankerDrivers as MutableList<TankerDriver>
   }

   suspend fun getAllOrders(userId:String,userEmail:String):MutableList<AllOrder>{
      return apiService.getAllOrders("Ok", API_KEY,userId,userEmail).body()!!.AllOrders as MutableList<AllOrder>
   }

   suspend fun getAllRevenue(userId:String,userEmail:String):MutableList<RevenueDetail>{
      return apiService.getAllRevenue("Ok", API_KEY,userId,userEmail).body()!!.RevenueDetails as MutableList<RevenueDetail>
   }

   suspend fun getDetailedOrderDetails(customerID:String ,userId:String,userEmail:String):MutableList<AllDetailedOrder>{
      return apiService.getDetailedOrderReport("Ok", API_KEY,customerID,userId,userEmail).body()!!.AllDetailedOrder as MutableList<AllDetailedOrder>
   }


   suspend fun addDriver( driverName:String, driverEmail:String, driverMobile:String, driverPass:String): Response<AddDriverResponseDTO> {
      return  apiService.addDriver("Ok",API_KEY,driverName,driverEmail, driverMobile,driverPass,"Tejeet")
   }

   suspend fun userLogin( userId:String, userPass:String, firebaseToken:String, smartphone:String,smartphoneModel:String, apiLevel:String,androidversion:String): Response<LoginResponseDTO> {
      return  apiService.userLogin("Ok",API_KEY,userId,userPass, firebaseToken, smartphone, smartphoneModel, apiLevel, androidversion)
   }

   suspend fun userFirebaseTokenUpdate( userEmail:String, userID:String, firebaseToken:String): Response<UserFBTokenUpdateResponseDTO> {
      return  apiService.userFirebaseTokenUpdate("OK",API_KEY,userEmail,userID, firebaseToken)
   }


}