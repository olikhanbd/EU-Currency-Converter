package com.oli.eucurrencyconverter

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.oli.eucurrencyconverter.adapters.CountryAdapter
import com.oli.eucurrencyconverter.models.Rate
import com.oli.eucurrencyconverter.models.Rates
import com.oli.eucurrencyconverter.models.VatResponseModel
import com.oli.eucurrencyconverter.rest.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    lateinit var adapter: CountryAdapter
    var rate: MutableList<Rate> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        fetchDataFromServer()
    }

    private fun initView() {

        adapter = CountryAdapter(this, 0, rate)
        spinnerCountry.adapter = adapter

        spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                radioGroup.clearCheck()
                radioGroup.check(R.id.rbStandard)
                setVatData(rate[position].periods!!.get(0).rates)
            }
        }

        et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    try {
                        val value = s.toString().toDouble()
                        calculateAmount(value)
                    } catch (e: Exception) {
                        resetCalculation()
                        Log.e(tag, e.toString())
                    }
                } else {
                    resetCalculation()
                }
            }

        })

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val s: String = et.text.toString()

            if (s.isNotEmpty()) {
                try {
                    val value = s.toDouble()
                    calculateAmount(value)
                } catch (e: Exception) {
                    resetCalculation()
                    Log.e(tag, e.toString())
                }
            } else {
                resetCalculation()
            }
        }
    }

    fun setVatData(rates: Rates) {

        if (rates.standard == null)
            rbStandard.visibility = View.GONE
        else {
            rbStandard.visibility = View.VISIBLE
            rbStandard.text = getString(R.string.standard, rates.standard)
        }
        if (rates.superReduced == null)
            rbSuperReduced.visibility = View.GONE
        else {
            rbSuperReduced.visibility = View.VISIBLE
            rbSuperReduced.text = getString(R.string.super_reduced, rates.superReduced)
        }
        if (rates.reduced == null)
            rbReduced.visibility = View.GONE
        else {
            rbReduced.visibility = View.VISIBLE
            rbReduced.text = getString(R.string.reduced, rates.reduced)
        }
        if (rates.reduced1 == null)
            rbReduced1.visibility = View.GONE
        else {
            rbReduced1.visibility = View.VISIBLE
            rbReduced1.text = getString(R.string.reduced1, rates.reduced1)
        }
        if (rates.reduced2 == null)
            rbReduced2.visibility = View.GONE
        else {
            rbReduced2.visibility = View.VISIBLE
            rbReduced2.text = getString(R.string.reduced2, rates.reduced2)
        }
        if (rates.parking == null)
            rbParking.visibility = View.GONE
        else {
            rbParking.visibility = View.VISIBLE
            rbParking.text = getString(R.string.parking, rates.parking)
        }
    }

    fun calculateAmount(value: Double) {

        val vatRates: Rates = rate[spinnerCountry.selectedItemPosition].periods!!.get(0).rates

        tvOriginalAmount.text = value.toString()

        var vat = 0.0

        when (radioGroup.checkedRadioButtonId) {
            R.id.rbStandard -> {
                vat = value * (vatRates.standard!!.div(100))
            }
            R.id.rbSuperReduced -> {
                vat = value * (vatRates.superReduced!!.div(100))
            }
            R.id.rbReduced -> {
                vat = value * (vatRates.reduced!!.div(100))
            }
            R.id.rbReduced1 -> {
                vat = value * (vatRates.reduced1!!.div(100))
            }
            R.id.rbReduced2 -> {
                vat = value * (vatRates.reduced2!!.div(100))
            }
            R.id.rbParking -> {
                vat = value * (vatRates.parking!!.div(100))
            }
        }

        tvTax.text = String.format("%.1f", vat)

        tvTotal.text = String.format("%.1f", value.plus(vat))

    }

    fun resetCalculation() {
        tvOriginalAmount.text = getString(R.string.not_set)
        tvTax.text = getString(R.string.not_set)
        tvTotal.text = getString(R.string.not_set)
    }

    private fun fetchDataFromServer() {

        progressLayout.visibility = View.VISIBLE

        ApiClient.instance.getVatData("https://jsonvat.com/")
            .enqueue(object : Callback<VatResponseModel> {
                override fun onFailure(call: Call<VatResponseModel>, t: Throwable) {
                    progressLayout.visibility = View.GONE
                    Log.d(tag, t.message)
                    showSnackBar(t.message.toString())
                }

                override fun onResponse(call: Call<VatResponseModel>, response: Response<VatResponseModel>) {

                    progressLayout.visibility = View.GONE

                    Log.d(
                        tag, "fetchDataFromServer isSuccessful: " +
                                "${response.isSuccessful} code: ${response.code()}"
                    )

                    rate.addAll(response.body()?.rates!!)
                    adapter.notifyDataSetChanged()

                }

            })
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT)
    }
}
