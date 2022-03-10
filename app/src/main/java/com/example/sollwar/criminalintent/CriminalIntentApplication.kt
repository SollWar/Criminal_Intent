package com.example.sollwar.criminalintent

import android.app.Application

/**
 * Класс для получения жизненного цикла самого приложения
 * Для использования его необходимо зарегистрировать в манифесте android:name
 */
class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this) // Создаётся один раз при запуске приложения
    }
}