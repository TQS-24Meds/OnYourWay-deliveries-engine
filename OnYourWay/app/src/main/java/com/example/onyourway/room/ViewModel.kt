package com.example.onyourway.room

import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DeliveryViewModel(private val repository: DeliveryRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var rider: Rider? = null
    val allDeliveries: LiveData<List<Delivery>> = repository.allDeliveries.asLiveData()
    fun getRiderWithEmail(email:String)= viewModelScope.launch {

             rider= repository.getRiderWithEmail(email) ////I want to use this value outside the scope


    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertDelivery(delivery: Delivery) = viewModelScope.launch {
        repository.insertDelivery(delivery)
    }
    fun insertRide(ride: Ride) = viewModelScope.launch {
        repository.insertRide(ride)
    }
    fun inserRider(rider: Rider) = viewModelScope.launch {
        repository.inserRider(rider)
    }
}

class ViewModelFactory(private val repository: DeliveryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeliveryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
