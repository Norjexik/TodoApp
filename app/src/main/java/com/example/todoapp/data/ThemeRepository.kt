package com.example.todoapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_settings")

class ThemeRepository(context: Context) {
    private val dataSore = context.themeDataStore

    private val KEY_THEME = booleanPreferencesKey("dark_theme")

    val isDarkTheme: Flow<Boolean> = dataSore.data.map { preferences ->
        preferences[KEY_THEME] ?: false
    }

    suspend fun setDarkTheme(enabled: Boolean){
        dataSore.edit { preferences ->
            preferences[KEY_THEME] = enabled
        }
    }
}