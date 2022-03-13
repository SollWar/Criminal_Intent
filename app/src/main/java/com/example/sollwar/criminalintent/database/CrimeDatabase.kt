package com.example.sollwar.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sollwar.criminalintent.Crime

/**
 * Класс отражающий базу данных
 */
@Database(entities = [Crime::class], version = 2) // Указывает что класс это БД и список таблиц этой БД + версия базы данных
@TypeConverters(CrimeTypeConverters::class) // класс для преобразования типов
abstract class CrimeDatabase: RoomDatabase() { // Сделать abstract и экземпляр RoomDatabase
    abstract fun crimeDao(): CrimeDao // Подключение DAO к интерфейсу
}

val migration_1_2 = object: Migration(1, 2) { // Обновление базы данных
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL( // Для обновление БД добавляем нужный столбец
            "ALTER TABLE Crime ADD COLUMN suspect TEXT NOT NULL DEFAULT ''"
        )
    }
}