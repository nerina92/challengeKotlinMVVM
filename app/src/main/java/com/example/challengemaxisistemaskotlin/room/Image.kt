package com.example.challengemaxisistemaskotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
class Image (@ColumnInfo(name = "url") val url: String){
    @PrimaryKey(autoGenerate = true)
    val idImage:Int=0
}