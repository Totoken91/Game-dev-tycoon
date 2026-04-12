package com.gamedevstudio.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "game_prefs")

@Singleton
class GamePreferences @Inject constructor(
    private val context: Context
) {
    companion object {
        val STUDIO_NAME = stringPreferencesKey("studio_name")
        val LAST_TICK_TIMESTAMP = longPreferencesKey("last_tick_timestamp")
        val MUSIC_ENABLED = booleanPreferencesKey("music_enabled")
        val SFX_ENABLED = booleanPreferencesKey("sfx_enabled")
        // TODO: Add speed preference, premium status, tutorial completion
    }

    val studioName: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[STUDIO_NAME] ?: ""
    }

    val lastTickTimestamp: Flow<Long> = context.dataStore.data.map { prefs ->
        prefs[LAST_TICK_TIMESTAMP] ?: 0L
    }

    suspend fun setStudioName(name: String) {
        context.dataStore.edit { prefs -> prefs[STUDIO_NAME] = name }
    }

    suspend fun setLastTickTimestamp(timestamp: Long) {
        context.dataStore.edit { prefs -> prefs[LAST_TICK_TIMESTAMP] = timestamp }
    }

    // TODO: Implement remaining preference accessors
}
