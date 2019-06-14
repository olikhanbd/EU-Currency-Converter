package com.oli.eucurrencyconverter.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rates {
    @SerializedName("super_reduced")
    @Expose
    private var superReduced: Double? = null
    @SerializedName("reduced")
    @Expose
    private var reduced: Double? = null
    @SerializedName("standard")
    @Expose
    private var standard: Double? = null
    @SerializedName("reduced1")
    @Expose
    private var reduced1: Double? = null
    @SerializedName("reduced2")
    @Expose
    private var reduced2: Double? = null
    @SerializedName("parking")
    @Expose
    private var parking: Double? = null

    fun getSuperReduced(): Double? {
        return superReduced
    }

    fun setSuperReduced(superReduced: Double?) {
        this.superReduced = superReduced
    }

    fun getReduced(): Double? {
        return reduced
    }

    fun setReduced(reduced: Double?) {
        this.reduced = reduced
    }

    fun getStandard(): Double? {
        return standard
    }

    fun setStandard(standard: Double?) {
        this.standard = standard
    }

    fun getReduced1(): Double? {
        return reduced1
    }

    fun setReduced1(reduced1: Double?) {
        this.reduced1 = reduced1
    }

    fun getReduced2(): Double? {
        return reduced2
    }

    fun setReduced2(reduced2: Double?) {
        this.reduced2 = reduced2
    }

    fun getParking(): Double? {
        return parking
    }

    fun setParking(parking: Double?) {
        this.parking = parking
    }
}