package com.example.challengemaxisistemaskotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table", foreignKeys = [ForeignKey(
   entity = Image::class,
   parentColumns = arrayOf("idImage"),
   childColumns = arrayOf("idImage"),
   onDelete = ForeignKey.CASCADE
)])
data class Breed ( @PrimaryKey(autoGenerate = true)var id : Int,
                       @ColumnInfo(name = "breedName") var breedName : String,
                       @ColumnInfo(name="idImage") var idImage : Int?){

   constructor():this(0,"",null)
}