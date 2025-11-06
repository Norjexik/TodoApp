package com.example.todoapp.data.repository

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
    private val dataStore = context.themeDataStore
    private val darkThemeKey = booleanPreferencesKey("dark_theme")
    val isDarkTheme: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[darkThemeKey] ?: false
        }

    suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[darkThemeKey] = enabled
        }
    }
}