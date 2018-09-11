package base.smartservices.com.cache

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "base.smartservices.com.minion.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val minionPref: SharedPreferences

    init {
        minionPref = context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = minionPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = minionPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()
}