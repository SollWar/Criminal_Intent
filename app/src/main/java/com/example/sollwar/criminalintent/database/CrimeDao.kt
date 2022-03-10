package com.example.sollwar.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.sollwar.criminalintent.Crime
import java.util.*

/**
 * DAO - объект доступа к данным
 * Интерфейс для работы с БД
 */
@Dao // Сообщить что это интерфейс DAO
interface CrimeDao {
    // @Query - указывает что это извлечение из БД
    // в параметрах SQL запрос
    // Возвращают LiveData для работы в отдельном потоке
    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
}