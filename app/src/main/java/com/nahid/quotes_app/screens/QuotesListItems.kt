package com.nahid.quotes_app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nahid.quotes_app.R
import com.nahid.quotes_app.models.Quotes

@Composable
fun QuotesListItems(quotes: Quotes, onClick: (quotes: Quotes) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.LightGray),
        modifier = Modifier
            .clickable { onClick(quotes) }
            .padding(8.dp)
            .border(1.dp, Color.Cyan, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_quote),
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .rotate(180f)
                    .border(1.dp, Color.Cyan, RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .border(1.dp, Color.Cyan, RoundedCornerShape(4.dp)),
                contentDescription = "Quote"
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = quotes.text?:"XYZ",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Divider()
                Text(
                    text = quotes.author?:"XYZ",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 2.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}
