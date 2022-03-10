package com.example.sollwar.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sollwar.criminalintent.Crime

/**
 * Класс отражающий базу данных
 */
@Database(entities = [Crime::class], version = 1) // Указывает что класс это БД и список таблиц этой БД + версия базы данных
@TypeConverters(CrimeTypeConverters::class) // класс для преобразования типов
abstract class CrimeDatabase: RoomDatabase() { // Сделать abstract и экземпляр RoomDatabase
    abstract fun crimeDao(): CrimeDao // Подключение DAO к интерфейсу
}