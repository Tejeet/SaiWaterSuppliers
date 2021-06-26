package com.tejeet.saiwatersuppliers.Repository

import com.tejeet.saiwatersuppliers.Constant.ConstantsData.API_KEY
import com.tejeet.saiwatersuppliers.Data.ModelDTO.AddUserResponseDTO
import com.tejeet.saiwatersuppliers.Network.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(
   private val apiService: ApiService
){

   suspend fun addUser(
      societyName:String,
      customerName:String,
      customerEmail:String,
      customerMobile: String,
      customerAddress: String,
      tankerRate: String,
      adminName: String,
   ): Response<AddUserResponseDTO> {

      return  apiService.addUser("Ok",API_KEY,societyName,customerName,
      customerEmail,customerMobile,customerAddress,tankerRate,"123456",adminName)
   }


}