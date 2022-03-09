package com.example.sollwar.criminalintent

import androidx.lifecycle.ViewModel

/**
 * ViewModel фрагмента CrimeList
 * Пока не хранит, а каждый раз генерирует 100 объектов Crime
 */
class CrimeListViewModel: ViewModel() {
    val crimes = mutableListOf<Crime>() // Список crime
    init {
        for (i in 0 until 100) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
}