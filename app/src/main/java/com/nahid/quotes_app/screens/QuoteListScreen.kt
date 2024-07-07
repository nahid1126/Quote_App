package com.nahid.quotes_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nahid.quotes_app.models.Quotes

@Composable
fun QuotesListScreen(quoteList: Array<Quotes>, onClick: (quotes: Quotes) -> Unit) {
    Column {
        Text(
            text = "Quote App",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 40.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(1f),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        QuoteList(data = quoteList, onClick)
    }
}

@Composable
fun QuoteList(data: Array<Quotes>, onClick: (quotes: Quotes) -> Unit) {
    LazyColumn (userScrollEnabled = true){
        items(data) {
            QuotesListItems(quotes = it, onClick)
        }
    }
}