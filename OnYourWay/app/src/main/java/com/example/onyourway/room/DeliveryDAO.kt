package com.example.onyourway.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao

interface DeliveryDAO {

        @Query("SELECT * FROM delivery_table")
        fun getDeliveries(): Flow<List<Delivery>>
        @Query("SELECT * FROM rider_table where email =:email")
        fun getRiderWithEmail(email:String ): Rider
        @Query("SELECT * FROM rider_table where id =:id")
        fun getRiderFromId(id:Int ): Rider
        @Query("SELECT * FROM ride_table where id =:id")
        fun getRideFromId(id:Int  ): Ride
        @Query("SELECT * FROM delivery_table where id =:id")
        fun getDeliveryFromId(id:Int ): Delivery
        @Query("SELECT * FROM ride_table where rider_id =:rider_id")
        fun getRidesForRider(rider_id:Int ): Flow<List<Ride>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertDelivery(delivery: Delivery)
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertRide(ride: Ride)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertRider(rider: Rider)

        @Query("DELETE FROM rider_table")
        suspend fun deleteAllRider()
        @Query("DELETE FROM ride_table")
        suspend fun deleteAllRide()
        @Query("DELETE FROM delivery_table")
        suspend fun deleteAllDelivery()

}