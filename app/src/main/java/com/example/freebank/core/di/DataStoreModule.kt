package com.example.freebank.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.freebank.core.data.datastore.AuthPreferences
import com.example.freebank.core.data.remote.authenticator.TokenManager
import com.example.freebank.core.data.remote.authenticator.TokenManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.datastore by preferencesDataStore(
    name = "auth_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        context.datastore

    @Provides
    @Singleton
    fun provideAuthPreferences(
        dataStore: DataStore<Preferences>
    ): AuthPreferences =
        AuthPreferences(dataStore)

    @Provides
    @Singleton
    fun provideTokenManager(
        authPreferences: AuthPreferences
    ): TokenManager =
        TokenManagerImpl(authPreferences)
}