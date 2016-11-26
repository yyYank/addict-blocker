package com.github.yyyank.addict.blocker.preferences

import android.content.Context
import android.util.Log


/**
 * Created by yy_yank on 2016/11/26.
 */
object AddictBlockerSettings {


    fun <V>save(context : Context, key : String, value : V) {
        val data = context.getSharedPreferences("AddictBlockerSettings", Context.MODE_PRIVATE)

        Log.d("AddictBlocker", "AddictBlockerSettings save")
        Log.d("AddictBlocker", value.toString())
        // 編集モード
        data.edit().apply {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> throw IllegalArgumentException("それはだめ")
            }
        }.apply()

    }

    fun load(context : Context, key : String, type : SettingsType) : SettingValue<*>? {
        Log.d("AddictBlocker", "AddictBlockerSettings load")
        Log.d("AddictBlocker", "key is  $key")
        val data = context.getSharedPreferences("AddictBlockerSettings", Context.MODE_PRIVATE)
            return when (type) {
                SettingsType.String -> SettingValue(data.getString(key, ""))
                SettingsType.Int -> SettingValue(data.getInt(key, -1))
                SettingsType.Boolean -> SettingValue(data.getBoolean(key, false))
                SettingsType.Float -> SettingValue((data.getFloat(key, -1f)))
                SettingsType.Long -> SettingValue(data.getLong(key, -1L))
                else -> throw IllegalArgumentException("それはだめ")
            }
    }



}

class SettingValue<V>(value : V) {
    val value : V = value
}

enum class SettingsType {
    String,
    Int,
    Boolean,
    Float,
    Long
}