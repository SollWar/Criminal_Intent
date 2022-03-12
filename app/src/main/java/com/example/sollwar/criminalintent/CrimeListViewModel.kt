package com.example.sollwar.criminalintent

import androidx.lifecycle.ViewModel

/**
 * ViewModel фрагмента CrimeList
 */
class CrimeListViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes() // Наблюдает за LiveData из CrimeRepository
    fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }

}