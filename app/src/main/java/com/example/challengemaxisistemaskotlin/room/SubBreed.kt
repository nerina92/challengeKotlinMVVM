package com.example.challengemaxisistemaskotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subBreed_table")
class SubBreed (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "subBredName") val subBredName: String,
    @ColumnInfo(name="idImage") val idImage:Int)