package com.example.sollwar.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class CrimeDetailViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get() // Хранится связь с CrimeRepository
    private val crimeIdLiveData = MutableLiveData<UUID>() // Хранит id отображаемого в данный момент фрагмента

    // Т.к. crimeLiveData публичен, нужно убедится что это не MutableLiveData
    // ViewModel никогда не должны выставлять публично MutableLiveData
    // Transformation (преобразование данных в реальном времени) - отношение триггер - ответ между двумя LiveData
    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(crimeIdLiveData) { crimeId ->
        crimeRepository.getCrime(crimeId)
    }

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }
}