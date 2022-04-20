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

    @Query ("SELECT * FROM subBreed_table WHERE idBreed=:idBreed")
    suspend fun searchSubBreeds(idBreed: Int):List<SubBreed>

    @Query ("UPDATE subBreed_table SET idImage =:idImage WHERE subBredName=:breedName;")
    suspend fun update(idImage: Long, breedName:String)

    @Query ("SELECT idImage FROM subBreed_table WHERE subBredName=:name")
    suspend fun searchImage(name:String) : Int
}