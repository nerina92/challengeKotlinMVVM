package com.example.challengemaxisistemaskotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed_table")
    suspend fun getBreeds(): List<Breed>

    @Insert
    suspend fun insert(breed: Breed)

    @Query("UPDATE breed_table SET idImage =:idImage WHERE breedName=:breedName;")
    suspend fun update(idImage: Long, breedName:String)

    @Query("DELETE FROM breed_table")
    suspend fun deleteAll()

    @Query ("SELECT idImage FROM breed_table WHERE breedName=:name")
    suspend fun searchImage(name:String) : Int

    @Query ("SELECT id FROM breed_table WHERE breedName=:name")
    suspend fun searchId(name:String) : Int
}