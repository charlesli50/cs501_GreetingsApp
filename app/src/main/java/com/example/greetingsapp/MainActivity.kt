package com.example.greetingsapp

import android.R.attr.text
import android.R.attr.value
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.greetingsapp.ui.theme.GreetingsAppTheme

import java.time.LocalDateTime
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {


//    var clicked by remember { mutableStateOf(false)}
    var typed by remember { mutableStateOf("") }

    val currentTime = remember {
        LocalDateTime.now().hour
    }

//    var greet = ""
    var greet = when (currentTime) {
        in 0..6 -> "Late Night"
        in 6..12 -> "Morning"
        in 12..18 -> "Afternoon"
        else -> "Evening"
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Greeting App!",
            modifier = Modifier
                .wrapContentWidth()
//                .wrapContentHeight(align = Alignment.Horizontal)
                .padding(bottom = 16.dp),
        )

        Text(
            text = if (typed.length > 0) "Hello ${typed}, \nGood $greet!" else "",
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
                .padding(bottom = 16.dp),

//            style = TextStyle(
//                fontWeight = FontWeight.Bold,
//                color = Color.Cyan,
//                background = Color.Black,
//                fontSize = 36.sp,
//                textAlign = TextAlign.Center
//            )
        )

        TextField(
            value = typed,
            onValueChange = { typed = it },
            label = { Text("Input your name: ") }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingsAppTheme {
        Greeting()
    }
}