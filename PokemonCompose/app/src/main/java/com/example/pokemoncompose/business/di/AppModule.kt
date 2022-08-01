package com.example.pokemoncompose.business.di

import android.content.Context
import androidx.room.Room
import com.example.pokemoncompose.PokeApp
import com.example.pokemoncompose.business.persistance.AppDatabase
import com.example.pokemoncompose.business.persistance.PokeDetailDao
import com.example.pokemoncompose.business.persistance.PokemonDao
import com.example.pokemoncompose.business.persistance.TypeArrayConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is the Global Hilt Module that provides:
 *
 * 1.) Access to Room Database
 * 2.) Data Access Objects for API
 * 3.) Moshi / JSON converter objects
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) : PokeApp = app as PokeApp

    @Singleton
    @Provides
    fun provideAppDatabase(app: PokeApp,converter: TypeArrayConverter) : AppDatabase {

        return Room
            .databaseBuilder(app,AppDatabase::class.java,"Pokemon.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(converter)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi() : Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(appDatabase: AppDatabase) : PokemonDao {
        return appDatabase.pokemonDao()
    }

    @Singleton
    @Provides
    fun providePokemonDetailDao(appDatabase: AppDatabase) : PokeDetailDao {
        return appDatabase.pokemonDetailDao()
    }

    @Singleton
    @Provides
    fun provideTypeArrayConverter(moshi: Moshi) : TypeArrayConverter {
        return TypeArrayConverter(moshi)
    }
}