package com.example.sollwar.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.time.format.DateTimeFormatter

private const val TAG = "CrimeListFragment"

/**
 * Класс фрагмента CrimeList
 * Работает с RecyclerView
 */
class CrimeListFragment: Fragment() {

    private lateinit var crimeRecyclerView: RecyclerView // Обьявление RecyclerView
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList()) // Адаптер для RecyclerView, при запуске пустой, пока LiveData без результатов


    private val crimeListViewModel: CrimeListViewModel by lazy { // Объявленеи ViewModel
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false) // Указывает layout
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context) // Нужен для работы RecyclerView
        crimeRecyclerView.adapter = adapter // Указываем адаптер
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // Вызывается после onCreateView, когда всё на своих местах
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe( // Регистриция наблюдателя за экземпляром LiveData и его связи с жизненным циклом другого компонента
                                            // Observer - отвечает за реакцию на новые данные из LiveData
            viewLifecycleOwner,     // Время жизни наблюдатели длится столько-же как и время фрагмента(в данном случае)
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }
            }
        )
    }

    /**
     * "Запускает" RecyclerView
     */
    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeAdapter(crimes) // Передаёт в adapter массив
        crimeRecyclerView.adapter = adapter
    }

    /**
     * Класс Holder "Обработка отдельного предмета RecyclerView"
     */
    private inner class CrimeHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener { // Контейнер для отдельного фрагмента Crime
        private lateinit var crime: Crime

        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)

        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Toast.makeText(context, "${crime.title} pressed", Toast.LENGTH_SHORT).show()
        }

        fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()
            solvedImageView.visibility = if (crime.isSolved) { // В зависимости от переменной скрывать ImageView
                View.VISIBLE
            } else {
                View.GONE
            }
        }

    }

    /**
     *  Класс Adapter "Показывает отдельные предметы на RecyclerView"
     */
    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {

        /**
         * Инициализирует представление отдельного предмета RecyclerView
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        /**
         * Выполняет присвоение и отображение
         */
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.apply {
                holder.bind(crime) // Выполнять присвоение и отображение в CrimeHolder( в холдере)
            }
        }

        override fun getItemCount() = crimes.size

    }

    companion object { // Аналог newIntent() - позволяет activity получить экземпляр фрагмента
        fun newInstance(): CrimeListFragment{
            return CrimeListFragment()
        }
    }
}