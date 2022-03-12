package com.example.sollwar.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

/**
 * Фрагмент с диалогом выбора даты
 */
class DatePickerFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date // Пролучает date
        val calendar = Calendar.getInstance()
        calendar.time = date // Преобразовывает date в календарь что-бы получить целочисленные значения для работы DataPicker
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        val dateListener = DatePickerDialog.OnDateSetListener { // Используется для получения выбранной пользователем даты
                _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            targetFragment?.let { fragment -> // в targetFragment храниться экземпляр который запустил этот фрагмент
                (fragment as Callbacks).onDateSelected(resultDate)
            }
        }

        return DatePickerDialog( // Возвращает DatePickerDialog, выводит на экран
            requireContext(), // Контекстный объект, в данном случае, тот кто вызвал
            dateListener, // Слушатель
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object { // Создаёт фрагмент с параметрами, как конструктор
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }

    interface Callbacks { // Интерфейс обратного вызова
        fun onDateSelected(date: Date)
    }
}