package hossein.gheisary.data.remote.core

import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.SearchResult
import io.reactivex.Single
import retrofit2.http.*


interface  Restapi {

    @Headers("Content-Type: application/json")
    @GET(".")
    fun searchFilms(@Query("apiKey") apiKey: String,
                    @Query("s") searchKey: String,
                    @Query("page") page: Int): Single<SearchResult>


    @Headers("Content-Type: application/json")
    @GET(".")
    fun getFilmDetails(@Query("apiKey") apiKey: String,
                       @Query("i") id: String): Single<FilmDetailsResponse>

}