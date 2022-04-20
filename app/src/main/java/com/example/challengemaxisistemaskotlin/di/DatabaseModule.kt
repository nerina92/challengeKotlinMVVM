package com.example.challengemaxisistemaskotlin.di

import android.app.Application
import androidx.room.Room
import com.example.challengemaxisistemaskotlin.room.BreedDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import com.example.challengemaxisistemaskotlin.room.BreedsRoomDatabase
import com.example.challengemaxisistemaskotlin.room.ImageDao
import com.example.challengemaxisistemaskotlin.room.SubBreedDao

val databaseModule = module {
    fun provideDataBase(application: Application): BreedsRoomDatabase {
        return Room.databaseBuilder(application, BreedsRoomDatabase::class.java, "breeds_database")
            //.fallbackToDestructiveMigration()
            .build()
    }
    //El single devuelve una instancia única de la clase, un singletón
    single { provideDataBase(androidApplication()) }
    /*single {
        BreedsRoomDatabase.getDatabase(androidApplication())
    }*/
    fun provideBreedDao(dataBase: BreedsRoomDatabase): BreedDao {
        return dataBase.breedDao()
    }
    fun provideImageDao(dataBase: BreedsRoomDatabase): ImageDao {
        return dataBase.imageDao()
    }
    fun provideSubBreedDao(dataBase: BreedsRoomDatabase): SubBreedDao {
        return dataBase.subBreedDao()
    }

    single { provideBreedDao(get()) }
    single { provideImageDao(get()) }
    single { provideSubBreedDao(get()) }
}
