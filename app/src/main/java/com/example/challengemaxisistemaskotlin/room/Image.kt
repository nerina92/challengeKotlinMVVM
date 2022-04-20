package com.example.challengemaxisistemaskotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class Image ( @PrimaryKey(autoGenerate = true)var idImage : Int,
    @ColumnInfo(name = "url")  var url: String){

    constructor():this(0,"")
}