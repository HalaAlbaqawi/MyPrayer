package com.example.prayerproject.repositories

import android.content.Context
import com.example.prayerproject.api.DuaaApi
import com.example.prayerproject.model.DuaaModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://61ae0fdca7c7f3001786f5c4.mockapi.io"

class ApiServiceAthkarRepository(val context: Context) {



    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //API FOR RETROFIT
    private val retrofitApi = retrofitService.create(DuaaApi::class.java)



   suspend fun getAthkar() = retrofitApi.getAthkar()
   suspend fun getMyAthkar(userid: String) = retrofitApi.getMyAthkar(userid)
   suspend fun addAthkar(athkarModel: DuaaModel)= retrofitApi.addAthkar(athkarModel)
   suspend fun editAthkar(athkarModel: DuaaModel)= retrofitApi.editAthkar(athkarModel.id,athkarModel)
   suspend fun deleteAthkar(id: String)=retrofitApi.deleteAthkar(id)

    companion object{
        private var instance: ApiServiceAthkarRepository? = null
        // design pattern
        fun init (conext: Context){

            if (instance == null)
                instance = ApiServiceAthkarRepository(conext)

        }
        fun get(): ApiServiceAthkarRepository {

            return instance ?: throw Exception("ApiServiceRepository must be initialized")
        }

    }

}