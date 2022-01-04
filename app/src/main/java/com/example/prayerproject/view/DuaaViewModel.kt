package com.example.prayerproject.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prayerproject.model.DuaaModel
import com.example.prayerproject.repositories.ApiServiceAthkarRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "AthkarViewModel"

class DuaaViewModel : ViewModel() {

    private val apiRepo = ApiServiceAthkarRepository.get()
    val duaaLiveData = MutableLiveData<List<DuaaModel>>()
    val LiveData = MutableLiveData<String>()
    val duaaErrorLiveData = MutableLiveData<String>()


    fun callData() {
        Log.d(TAG, "NO DATA")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getAthkar()

                response.body()?.run {
                    duaaLiveData.postValue(this)
                    Log.d(TAG, this.toString())
                }
                Log.d(TAG, response.message())
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                duaaErrorLiveData.postValue(e.message.toString())
            }
        }

    }
    fun addAthkar(athkarModel: DuaaModel) {

        Log.d(TAG, "check")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.addAthkar(
                    DuaaModel(
                        athkarModel.duaa, "", athkarModel.title,
                        FirebaseAuth.getInstance().currentUser!!.uid
                    )
                )
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        LiveData.postValue("Successful")
                    }
                }

                Log.d(TAG, response.message())

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                LiveData.postValue("UnSucessful")


            }
        }
    }
}