package com.oli.eucurrencyconverter.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Period {
    @SerializedName("effective_from")
    @Expose
    private var effectiveFrom: String? = null
    @SerializedName("rates")
    @Expose
    private var rates: Rates = Rates()

    fun getEffectiveFrom(): String? {
        return effectiveFrom
    }

    fun setEffectiveFrom(effectiveFrom: String) {
        this.effectiveFrom = effectiveFrom
    }

    fun getRates(): Rates {
        return rates
    }

    fun setRates(rates: Rates) {
        this.rates = rates
    }
}