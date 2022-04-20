package com.example.challengemaxisistemaskotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed_table")
    fun getBreeds(): List<Breed>

    @Insert
    fun insert(breed: Breed)

    @Query("UPDATE breed_table SET idImage =:idImage WHERE breedName=:breedName;")
    suspend fun update(idImage: Long, breedName:String)

    @Query("DELETE FROM breed_table")
    suspend fun deleteAll()
}