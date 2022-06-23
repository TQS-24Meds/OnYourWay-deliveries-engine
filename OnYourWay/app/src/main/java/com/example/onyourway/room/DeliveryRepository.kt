package com.example.onyourway.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class DeliveryRepository(private val dao: DeliveryDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    @Suppress("RedundantSuspendModifier")
    suspend fun  getDeliveryFromId(id : Int)= dao.getDeliveryFromId(id)
    @Suppress("RedundantSuspendModifier")
    suspend fun  getRideFromId(id : Int)= dao.getRideFromId(id)
    @Suppress("RedundantSuspendModifier")
    suspend fun  getRiderFromId(id : Int)= dao.getRiderFromId(id)
    @Suppress("RedundantSuspendModifier")
    suspend fun  getRideForRider(id : Int)= dao.getRidesForRider(id)

    @Suppress("RedundantSuspendModifier")
    suspend fun  getRiderWithEmail(email:String)= dao.getRiderWithEmail(email)

    val allDeliveries: Flow<List<Delivery>> =dao.getDeliveries()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDelivery(del: Delivery) {
        dao.insertDelivery(del)

    }
    suspend fun inserRider(rider: Rider) {
        dao.insertRider(rider)

    }
    suspend fun insertRide(ride: Ride) {
        dao.insertRide(ride)


    }

    suspend fun deleteDeliveries() {
        dao.deleteAllDelivery()

    }
    suspend fun deleteRiders() {
        dao.deleteAllRider()

    }
    suspend fun deleteRides() {
        dao.deleteAllRide()
    }
}