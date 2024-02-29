package com.hossam.football.shared

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {


    @Provides
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences =getEncryptedSharedPreferences(context)

}