package com.universe.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.universe.compose_practice.ui.theme.Compose_PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_PracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun DefaultPreview() {

    Column(){
        ListItemView(R.drawable.profile1,"Lisa","Android Developer")
        ListItemView(R.drawable.profile2,"Mark","BackEnd Engineer")
        ListItemView(R.drawable.profile3,"Hela","Cloud Architect")
        ListItemView(R.drawable.profile4,"henry","Full Stack Web Developer")
    }

}


@Composable
fun ListItemView(imgid:Int,name:String,occupation:String){

    Row(modifier = Modifier.padding(12.dp)){
        Image(
            painterResource(imgid),
            contentDescription = null,
            Modifier.size(40.dp)
        )
        Column(){

            Box(modifier = Modifier.padding(start=5.dp)){
                Text(text=name, fontWeight = FontWeight.Bold)
            }

            Box(modifier = Modifier.padding(start=5.dp)){
                Text(text=occupation, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}