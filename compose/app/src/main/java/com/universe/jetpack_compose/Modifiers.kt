package com.universe.jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.universe.jetpack_compose.ui.theme.Jetpack_ComposeTheme

class Modifiers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column (modifier = Modifier
                .padding(20.dp)
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .border(5.dp,Color.Magenta)
                .padding(5.dp)
                .border(5.dp,Color.Blue)
                .padding(5.dp)
                .border(10.dp,Color.Red)
                .padding(10.dp)
                .padding(20.dp)

            ){

                Text("Hello world", modifier = Modifier.padding(start=3.dp))
                Spacer(modifier = Modifier.height(40.dp))
                Text("My name is Fenil", modifier = Modifier.padding(start=3.dp))
                Spacer(modifier = Modifier.height(60.dp))
                Button(onClick = {
                    Toast.makeText(this@Modifiers,"Hey how are you doing",Toast.LENGTH_LONG).show()
                }){
                    Modifier.padding(20.dp)
                    Text("Click me to see the magic")
                }
            }

        }
    }
}
