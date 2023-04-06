package com.universe.compose_1

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.universe.compose_1.ui.theme.Compose_1Theme

class State : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
         TextInput()
        }
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun DefaultPreview3() {
 TextInput()
}


@Composable
fun TextInput(){
    val state= remember{mutableStateOf("")}
    TextField(
        value = state.value,
        onValueChange = {
            state.value=it
        },
        label = {
            Text(text = "Enter your Message")
        },

    )
}

