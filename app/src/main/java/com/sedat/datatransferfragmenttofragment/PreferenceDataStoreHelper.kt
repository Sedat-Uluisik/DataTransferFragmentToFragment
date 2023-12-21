package com.sedat.datatransferfragmenttofragment

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferenceDataStoreHelper(private val context: Context) {
    companion object {
        private const val NAME_DATA_STORE = "com.sedat.datatransferfragmenttofragment.datastore"
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = NAME_DATA_STORE)
        private val dataKey = stringPreferencesKey("com.sedat.datatransferfragmenttofragment.datastore.data")
    }

    suspend fun saveData(data: String) {
        context.dataStore.edit { preferences ->
            preferences[dataKey] = data
        }
    }

    fun getData(): Flow<String> = context.dataStore.data.catch { exception ->
        if(exception is IOException)
            emit(emptyPreferences())
        else
            throw exception
    }.map {preferences ->
        return@map preferences[dataKey] ?: "---"
    }

    suspend fun deleteData(){
        context.dataStore.edit { preferences ->
            preferences.remove(dataKey)
            //or
            //preferences.clear() clear all preferences
        }
    }
}