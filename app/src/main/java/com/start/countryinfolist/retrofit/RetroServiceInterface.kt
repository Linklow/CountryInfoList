package com.start.countryinfolist.retrofit

import com.start.countryinfolist.model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("v3.1/all")
    fun getCountryList(): Call<List<CountryModel>>
}