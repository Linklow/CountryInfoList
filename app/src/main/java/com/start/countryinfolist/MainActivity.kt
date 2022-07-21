package com.start.countryinfolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.countryinfolist.adapter.CountryListAdapter
import com.start.countryinfolist.databinding.ActivityMainBinding
import com.start.countryinfolist.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: CountryListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.countryListRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        binding.countryListRecyclerView.adapter = recyclerAdapter

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }
}