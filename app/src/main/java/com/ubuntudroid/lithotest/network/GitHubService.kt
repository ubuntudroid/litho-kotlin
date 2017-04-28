package com.ubuntudroid.lithotest.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ubuntudroid on 28.04.17.
 */
interface GitHubService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Repo>>
}