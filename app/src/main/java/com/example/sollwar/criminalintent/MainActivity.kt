package com.example.sollwar.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

private const val TAG = "MainActivity"

/**
 * Класс стартовой activity
 */
class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {

    override fun onCrimeSelected(crimeId: UUID) { // Вызывается из CrimeListFragment при нажатии на фрагмент и заменяет фрагмент
        val fragment = CrimeFragment.newInstance(crimeId)

        supportFragmentManager
            .beginTransaction() // Начинает транзакцию
            .replace(R.id.fragment_container, fragment) // Заменяет текущий фрагмент на другой
            .addToBackStack(null)  // Добавляет заменённый фрагмент в стек
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) // Возвращает фрагмент если он уже в списке
        if (currentFragment == null) { // Если нет фрагмента, создаёт новый
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager   // Транзакция фрагмента
                .beginTransaction() // Начинает транзакцию
                .add(R.id.fragment_container, fragment) // Добавляет фрагмент
                .commit()
        }
    }
}