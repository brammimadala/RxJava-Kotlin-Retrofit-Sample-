package com.lasys.rxjavatmdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lasys.rxjavatmdb.model.Movie
import com.lasys.rxjavatmdb.model.MovieDBResponse
import com.lasys.rxjavatmdb.repository.MovieRepository


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var movieRepository = MovieRepository(application)

    fun getAllMovies(): LiveData<List<Movie>> {
        return movieRepository.getMoviesLiveData()
    }

    fun clear() {
        movieRepository.clear()
    }


}