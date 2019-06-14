package com.oli.eucurrencyconverter.rest

import com.oli.eucurrencyconverter.models.VatResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface{

    @GET()
    fun getVatData(@Url url:String):Call<VatResponseModel>
}