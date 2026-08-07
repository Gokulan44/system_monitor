package com.soc.agent.database

import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONObject

/**
 * Room type converters that serialise list/map columns to JSON strings and
 * back. Used by entities that carry List<Double>, List<String>, List<Int> or
 * Map<String, Any> columns (CPU history, DNS servers, policy rules, ...).
 */
class Converters {

    // ---- List<String> ----

    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        if (value.isNullOrBlank()) return emptyList()
        return runCatching {
            val arr = JSONArray(value)
            List(arr.length()) { arr.getString(it) }
        }.getOrDefault(emptyList())
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String =
        list?.let { JSONArray(it).toString() } ?: "[]"

    // ---- List<Double> ----

    @TypeConverter
    fun fromDoubleList(value: String?): List<Double> {
        if (value.isNullOrBlank()) return emptyList()
        return runCatching {
            val arr = JSONArray(value)
            List(arr.length()) { arr.getDouble(it) }
        }.getOrDefault(emptyList())
    }

    @TypeConverter
    fun toDoubleList(list: List<Double>?): String =
        list?.let { JSONArray(it).toString() } ?: "[]"

    // ---- List<Int> ----

    @TypeConverter
    fun fromIntList(value: String?): List<Int> {
        if (value.isNullOrBlank()) return emptyList()
        return runCatching {
            val arr = JSONArray(value)
            List(arr.length()) { arr.getInt(it) }
        }.getOrDefault(emptyList())
    }

    @TypeConverter
    fun toIntList(list: List<Int>?): String =
        list?.let { JSONArray(it).toString() } ?: "[]"

    // ---- Map<String, Any> (policy rules) ----

    @TypeConverter
    fun fromRulesJson(value: String?): Map<String, Any> {
        if (value.isNullOrBlank()) return emptyMap()
        return runCatching {
            val obj = JSONObject(value)
            val out = HashMap<String, Any>()
            val keys = obj.keys()
            while (keys.hasNext()) {
                val key = keys.next() as String
                out[key] = obj.get(key)
            }
            out
        }.getOrDefault(emptyMap())
    }

    @TypeConverter
    fun toRulesJson(map: Map<String, Any>?): String =
        map?.let { JSONObject(it).toString() } ?: "{}"
}