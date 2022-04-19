package com.example.challengemaxisistemaskotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubBreedDao {
    @Query("SELECT * FROM subBreed_table")
    fun getSubBreeds(): List<SubBreed>

    @Insert
    suspend fun insert(subBreed: SubBreed)

    @Query("DELETE FROM subBreed_table")
    suspend fun deleteAll()
}