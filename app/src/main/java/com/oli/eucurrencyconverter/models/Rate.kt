package com.oli.eucurrencyconverter.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rate {
    @SerializedName("name")
    @Expose
    private var name: String = ""
    @SerializedName("code")
    @Expose
    private var code: String? = null
    @SerializedName("country_code")
    @Expose
    private var countryCode: String? = null
    @SerializedName("periods")
    @Expose
    private var periods: List<Period>? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun getCountryCode(): String? {
        return countryCode
    }

    fun setCountryCode(countryCode: String) {
        this.countryCode = countryCode
    }

    fun getPeriods(): List<Period>? {
        return periods
    }

    fun setPeriods(periods: List<Period>) {
        this.periods = periods
    }

}