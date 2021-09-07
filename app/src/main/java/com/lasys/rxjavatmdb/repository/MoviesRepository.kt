package com.lasys.rxjavatmdb.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lasys.rxjavatmdb.R
import com.lasys.rxjavatmdb.model.Movie
import com.lasys.rxjavatmdb.model.MovieDBResponse
import com.lasys.rxjavatmdb.service.MoviesDataService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor
    (
    app: Application,
    private var apiService: MoviesDataService
) {
    private var application = app
    private var compositeDisposable = CompositeDisposable()
    private var moviesLiveData = MutableLiveData<List<Movie>>()
    private var movies: ArrayList<Movie> = ArrayList()
    private lateinit var movieDBResponseObservable: Observable<MovieDBResponse>

    fun getMoviesLiveData(): MutableLiveData<List<Movie>> {

        val getMoviesDataService = apiService
        movieDBResponseObservable = getMoviesDataService.getPopularMoviesWithRx(
            application.applicationContext.getString(
                R.string.api_key
            )
        )

        compositeDisposable.add(
            movieDBResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { movieDBResponse -> Observable.fromIterable(movieDBResponse.results) }
                .filter { movie -> movie.voteAverage > 7.0 }
                .subscribeWith(object : DisposableObserver<Movie>() {
                    override fun onNext(movie: Movie) {
                        movies.add(movie)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("MovieRepository", "" + e.localizedMessage)
                    }

                    override fun onComplete() {
                        moviesLiveData.postValue(movies)
                    }

                })
        )

        return moviesLiveData

    }

}