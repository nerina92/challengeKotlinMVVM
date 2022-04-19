package com.example.challengemaxisistemaskotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table")
class Breed(@ColumnInfo(name = "breedName")var breedName: String) {
   @PrimaryKey(autoGenerate = true)
   var id: Int=0
   @ColumnInfo(name = "idImage")
   var idImage : Int=0
}