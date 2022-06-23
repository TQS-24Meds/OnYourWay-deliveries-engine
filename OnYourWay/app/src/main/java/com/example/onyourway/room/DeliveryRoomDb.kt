package com.example.onyourway.room


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Rider::class,Ride::class,Delivery::class), version = 1, exportSchema = false)
abstract class DeliveryRoomDB : RoomDatabase() {

    abstract fun deliveryDAO(): DeliveryDAO

    private class DeliveryRoomDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {


        override fun onCreate(db: SupportSQLiteDatabase) {
            Log.d("Database","Get")

            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var deliveryDAO = database.deliveryDAO()

                    // Delete all content here.
                    deliveryDAO.deleteAllDelivery()
                    deliveryDAO.deleteAllRider()
                    deliveryDAO.deleteAllRide()
                    var rider = Rider(null,"Rua dom manuel barbuda e vasconcelos aradas",5.11111,5.11111,"pylance@ua.pt","pylance123","Paulo Pereira","pylance","AVAILABLE",4.0)
                    deliveryDAO.insertRider(rider)
                    var delivery = Delivery(null,"R. Mário Sacramento 141",1,"DELIVERED")
                    deliveryDAO.insertDelivery(delivery)
                    delivery = Delivery(null,"R. Mário Sacramento 12",2,"DELIVERED")
                    deliveryDAO.insertDelivery(delivery)
                    var ride= Ride(null,"R. Mário Sacramento 141",1,1,"FINISHED")
                    deliveryDAO.insertRide(ride)
                    ride= Ride(null,"R. Mário Sacramento 12",2,1,"FINISHED")
                    deliveryDAO.insertRide(ride)


                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DeliveryRoomDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DeliveryRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeliveryRoomDB::class.java,
                    "DeliveryDB"
                )
                    .addCallback( DeliveryRoomDBCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance

                instance
            }
        }
    }
}
