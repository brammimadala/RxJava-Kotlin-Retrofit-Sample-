package com.lasys.rxjavatmdb.service

import com.lasys.rxjavatmdb.model.MovieDBResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesDataService {

    @GET("movie/popular")
    fun getPopularMoviesWithRx(@Query("api_key") apiKey: String?): Observable<MovieDBResponse>
}