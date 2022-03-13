package com.example.sollwar.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.sollwar.criminalintent.database.CrimeDatabase
import com.example.sollwar.criminalintent.database.migration_1_2
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

/**
 * Шаблон репозитория
 * Одноэлементный класс (синглтон) - существует только 1 его экземпляр, при закрытии приложения удаляется из памяти
 */
class CrimeRepository private constructor(context: Context){

    private val database: CrimeDatabase = Room.databaseBuilder( // Создаёт конкретную реализацию CrimeDatabase
        context.applicationContext, // Передаём контекст приложения
        CrimeDatabase::class.java, // Класс БД которую необходимо создать
        DATABASE_NAME // Имя файла БД
    ).addMigrations(migration_1_2) // Миграция БД
        .build()

    private val crimeDao = database.crimeDao() // Инициализация DAO и его функций
    private val executor = Executors.newSingleThreadExecutor() // Исполнитель - обыект который ссылается на поток
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)
    fun updateCrime(crime: Crime) {
        executor.execute { // Код,
            // который находится в этом блоке, будет выполняться в любом
            // потоке, на который ссылается исполнитель.
            crimeDao.updateCrime(crime)
        }
    }
    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}