package com.oli.eucurrencyconverter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.oli.eucurrencyconverter.R
import com.oli.eucurrencyconverter.models.Rate

class CountryAdapter(context: Context, val layoutResourceId: Int, val countryList: List<Rate>)
    : ArrayAdapter<Rate>(context, layoutResourceId, countryList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(convertView, parent, position, false)
    }

    override fun getItem(position: Int): Rate? {
        return countryList[position]
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(convertView, parent, position, true)
    }

    private fun createViewFromResource(convertView: View?, parent: ViewGroup,
                                       position: Int, isDropdown: Boolean): View{

        val context = parent.context

        val view = convertView?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item, parent, false)

        val rate = countryList.get(position)

        val tv:TextView = view.findViewById(R.id.tv)
        val iv:ImageView = view.findViewById(R.id.iv_spinner)
        tv.text = rate.name

        if(isDropdown)
            iv.visibility = View.GONE
        else iv.visibility = View.VISIBLE

        return view

    }
}