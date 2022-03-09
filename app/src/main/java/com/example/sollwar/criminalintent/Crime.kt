package com.example.sollwar.criminalintent

import java.util.*

/**
 * Класс описывающий Crime
 */
data class Crime(val id: UUID = UUID.randomUUID(),  // Генерация уникального идентификатора
                 var title: String = "",
                 var date: Date = Date(), // По-умолчанию текущая дата
                 var isSolved: Boolean = false) {

}