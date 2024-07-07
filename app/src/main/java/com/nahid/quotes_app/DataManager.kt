package com.nahid.quotes_app

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.nahid.quotes_app.models.Quotes

object DataManager {
    var data = emptyArray<Quotes>()
    var currentPage = mutableStateOf(Pages.LISTING)
    var isDataLoaded = mutableStateOf(false)
    fun loadDataFromFile(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quotes>::class.java)
        isDataLoaded.value = true
    }

    fun switchPages() {
        if (currentPage.value == Pages.LISTING) {
            currentPage.value = Pages.DETAILS
        } else {
            currentPage.value = Pages.LISTING
        }
    }
}