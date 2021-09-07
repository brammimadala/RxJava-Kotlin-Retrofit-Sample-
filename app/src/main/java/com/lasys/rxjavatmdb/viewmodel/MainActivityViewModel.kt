package com.lasys.rxjavatmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lasys.rxjavatmdb.model.Movie
import com.lasys.rxjavatmdb.repository.MoviesRepository
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
    private val movieRepository: MoviesRepository
) : ViewModel() {
    fun getAllMovies(): LiveData<List<Movie>> {
        return movieRepository.getMoviesLiveData()
    }

}