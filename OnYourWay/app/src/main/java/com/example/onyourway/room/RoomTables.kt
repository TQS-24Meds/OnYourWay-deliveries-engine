package com.example.onyourway.room




import androidx.room.*


@Entity(tableName = "rider_table")
class Rider(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "addr") val addr: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "name") val rname: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "rating") val rating: Double,


    )

@Entity(tableName = "delivery_table",  foreignKeys = [ForeignKey(entity =Ride::class, parentColumns = arrayOf("id"), childColumns = arrayOf("ride_id"), onDelete =ForeignKey.CASCADE)]
)

class Delivery(

    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "clientaddr") val caddr: String,
    @ColumnInfo(name = "ride_id") val ride_id: Int?,
    @ColumnInfo(name = "status") val status:String



    )
@Entity(tableName = "ride_table",foreignKeys = [ForeignKey(
    entity = Rider::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("rider_id"),
    onDelete = ForeignKey.CASCADE
),ForeignKey(
    entity = Delivery::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("package_id"),
    onDelete = ForeignKey.CASCADE
)])
class Ride(

    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "routestart") val caddr: String,
    @ColumnInfo(name = "package_id") val package_id: Int,
    @ColumnInfo(name = "rider_id") val rider_id: Int,
    @ColumnInfo(name="status") val status:String

    )





