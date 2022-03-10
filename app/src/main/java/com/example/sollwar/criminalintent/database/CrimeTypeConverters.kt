package com.example.sollwar.criminalintent.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Класс-конвертер для сложных типов данных, хранимых в таблице
 */

class CrimeTypeConverters {
    @TypeConverter // Дату в тип для хранения в таблице
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter // Дату из таблицы в объект Java
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter // UUID из таблицы в Java
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
    @TypeConverter // UUID в строку для таблицы
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}