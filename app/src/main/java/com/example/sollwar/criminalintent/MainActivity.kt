package com.example.sollwar.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Класс стартовой activity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) // Возвращает фрагмент если он уже в списке
        if (currentFragment == null) { // Если нет фрагмента, создаёт новый
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager   // Транзакция фрагмента
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}