package io.github.firelord.sharedPreferenceMultiplatform

import platform.Foundation.NSUserDefaults

actual class SharedPreferenceManager {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    actual fun putString(key: String, value: String) {
        userDefaults.setObject(value, key)
    }

    actual fun getString(key: String, defaultValue: String): String? {
        return userDefaults.stringForKey(key)
    }

    actual fun putInt(key: String, value: Int) {
        userDefaults.setInteger(value.toLong(), key)
    }

    actual fun getInt(key: String, defaultValue: Int): Int {
        return userDefaults.integerForKey(key).toInt()
    }

    actual fun putLong(key: String, value: Long) {
        userDefaults.setInteger(value, key)
    }

    actual fun getLong(key: String, defaultValue: Long): Long {
        return userDefaults.integerForKey(key)
    }

    actual fun putFloat(key: String, value: Float) {
        userDefaults.setFloat(value, key)
    }

    actual fun getFloat(key: String, defaultValue: Float): Float {
        return userDefaults.floatForKey(key)
    }

    actual fun putBoolean(key: String, value: Boolean) {
        userDefaults.setBool(value, key)
    }

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return userDefaults.boolForKey(key)
    }

    actual fun remove(key: String) {
        userDefaults.removeObjectForKey(key)
    }

    actual fun clear() {
        userDefaults.dictionaryRepresentation().keys.forEach {
            userDefaults.removeObjectForKey(it as String)
        }
    }
}