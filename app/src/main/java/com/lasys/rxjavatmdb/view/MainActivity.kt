package com.lasys.rxjavatmdb.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lasys.rxjavatmdb.R
import com.lasys.rxjavatmdb.adapter.CustomAdapter
import com.lasys.rxjavatmdb.model.Movie
import com.lasys.rxjavatmdb.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var movies: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.rvMovies)

        mainActivityViewModel = ViewModelProviders.of(this).get(
            MainActivityViewModel::class.java
        )
        getPopularMoviesRx()
    }

    private fun getPopularMoviesRx() {

        mainActivityViewModel.getAllMovies().observe(this, {
            movies = it as ArrayList<Movie>
            prepareRList(movies)
        })
    }

    private fun prepareRList(movies: ArrayList<Movie>) {
        Log.e("movieListSize ===> ", "" + movies.size)

        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(movies, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}