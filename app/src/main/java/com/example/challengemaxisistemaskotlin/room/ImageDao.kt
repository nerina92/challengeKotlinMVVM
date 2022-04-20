package com.example.challengemaxisistemaskotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDao {
    @Query("SELECT * FROM image_table")
    fun getImage(): List<Image>

    @Query("SELECT * FROM image_table WHERE idImage=:id")
    fun getImage(id:Int): Image

    @Insert
    suspend fun insert(image: Image) :Long

    @Query("DELETE FROM image_table")
    suspend fun deleteAll()

   /* @Query("SELECT MAX(idImage)  FROM image_table")
    suspend fun obtenerMaxId() : Int*/
}