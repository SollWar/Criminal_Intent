package com.example.sollwar.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CrimeListFragment"

class CrimeListFragment: Fragment() {

    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = null

    private val crimeListViewModel: CrimeListViewModel by lazy { // Объявленеи ViewModel
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context) // Нужен для работы RecyclerView
        updateUI()
        return view
    }

    private fun updateUI() {
        val crimes = crimeListViewModel.crimes
        adapter = CrimeAdapter(crimes) // Передаёт в adapter массив
        crimeRecyclerView.adapter = adapter
    }

    private inner class CrimeHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener { // Контейнер для отдельного фрагмента Crime

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Toast.makeText(context, "${crime.title} pressed", Toast.LENGTH_SHORT).show()
        }

        private lateinit var crime: Crime

        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)

        fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()
        }

    }

    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

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