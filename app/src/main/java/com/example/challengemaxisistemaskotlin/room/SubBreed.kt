package com.example.challengemaxisistemaskotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "subBreed_table" , foreignKeys = [ForeignKey(
    entity = Breed::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("idBreed"),
    onDelete = ForeignKey.CASCADE
), ForeignKey(
    entity = Image::class,
    parentColumns = arrayOf("idImage"),
    childColumns = arrayOf("idImage"),
    onDelete = ForeignKey.CASCADE
)])
data class SubBreed ( @PrimaryKey(autoGenerate = true)var idSubBreed : Int,
                      @ColumnInfo(name = "subBredName") var subBredName : String,
                      @ColumnInfo(name="idImage") var idImage : Int?,
                      @ColumnInfo(name="idBreed") var idBreed : Int?){

    constructor():this(0,"",null,null)
}