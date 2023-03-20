package com.universe.jetpack_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.universe.jetpack_compose.ui.theme.Jetpack_ComposeTheme

@Suppress("LABEL_RESOLVE_WILL_CHANGE")
class Row : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Row(modifier=Modifier
                .fillMaxSize()
                .background(Color.Green),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                Text("Never give up")
                Text("Life is not fair always")
                Text("Always keep fighting")
                Spacer(modifier = Modifier.height(50.dp))
            }

            Column (modifier = Modifier
                .background(Color.Black)

            ){
                Button(onClick = {
                    val intent: Intent= Intent(this@Row,Modifiers::class.java)
                    startActivity(intent)
                }){
                    Text("Goto new Window")
                }
            }
        }
    }
}

