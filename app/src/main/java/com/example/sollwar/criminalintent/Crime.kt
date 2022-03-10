package com.example.sollwar.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Класс описывающий Crime
 */

@Entity // Определяет класс как часть БД
data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),  // Генерация уникального идентификатора, он PrimaryKey в таблице
                 var title: String = "",
                 var date: Date = Date(), // По-умолчанию текущая дата
                 var isSolved: Boolean = false) {

}