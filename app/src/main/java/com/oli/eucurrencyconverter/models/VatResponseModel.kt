package com.oli.eucurrencyconverter.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VatResponseModel {

    @SerializedName("details")
    @Expose
    private var details: String? = null
    @SerializedName("version")
    @Expose
    private var version: Any? = null
    @SerializedName("rates")
    @Expose
    private var rates: List<Rate> = ArrayList()

    fun getDetails(): String? {
        return details
    }

    fun setDetails(details: String) {
        this.details = details
    }

    fun getVersion(): Any? {
        return version
    }

    fun setVersion(version: Any) {
        this.version = version
    }

    fun getRates(): List<Rate> {
        return rates
    }

    fun setRates(rates: List<Rate>) {
        this.rates = rates
    }
}