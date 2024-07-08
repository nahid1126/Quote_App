package com.nahid.quotes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nahid.quotes_app.screens.QuotesDetails
import com.nahid.quotes_app.screens.QuotesListScreen
import com.nahid.quotes_app.ui.theme.Quotes_AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadDataFromFile(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun App() {
    var theme by remember { mutableStateOf(false) }
    Quotes_AppTheme(theme) {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            Switch(
                checked = theme,
                onCheckedChange = { theme = it },
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    .align(Alignment.End),

                thumbContent = if (theme) {
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dark_mode),
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                } else {
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_day),
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                }
            )
            if (DataManager.isDataLoaded.value) {
                if (DataManager.currentPage.value == Pages.LISTING) {
                    QuotesListScreen(quoteList = DataManager.data) {
                        DataManager.switchPages(it)
                    }
                } else {
                    DataManager.currentQuote?.let { QuotesDetails(quote = it) }
                }
            } else {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)) {
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

enum class Pages { LISTING, DETAILS }
