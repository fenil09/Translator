package com.universe.compose_1

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.universe.compose_1.ui.theme.Compose_1Theme

class MainActivity : ComponentActivity() {
    lateinit var player:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.padding(12.dp)) {
                MarvelListItems(R.drawable.ironman,"IronMan","This is Iron Man")
                MarvelListItems(R.drawable.captainamerica,"CaptainAmerica","Captain in Action")
                MarvelListItems(R.drawable.hulk,"Hulk","Loves Gamma Radation")
                MarvelListItems(R.drawable.captainmarvel,"CaptainMarvel","Universe is my Home")
                MarvelListItems(R.drawable.thor,"Thor","Welcome to Asgard")
                MarvelListItems(R.drawable.strange,"DR Strange","Master of Mystic Arts")
                MarvelListItems(R.drawable.spiderman,"SpiderMan","Newyork is my Web")
            }
        }
    }
}



@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun DefaultPreview() {

    Column(){
        MarvelListItems(R.drawable.ironman,"IronMan","This is Iron Man")
        MarvelListItems(R.drawable.captainamerica,"CaptainAmerica","Captain in Action")
        MarvelListItems(R.drawable.hulk,"Hulk","Loves Gamma Radation")
        MarvelListItems(R.drawable.captainmarvel,"CaptainMarvel","Universe is my Home")
        MarvelListItems(R.drawable.thor,"Thor","Welcome to Asgard")
        MarvelListItems(R.drawable.strange,"DR Strange","Master of Mystic Arts")
        MarvelListItems(R.drawable.spiderman,"SpiderMan","Newyork is my Web")

        Row(){
            Image(
                painterResource(R.drawable.ic_play_1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
fun MarvelListItems(imgid:Int,name:String,description:String){

    Row(Modifier.padding(15.dp)){

        Image(
            painter = painterResource(imgid),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.size(85.dp)
                .clip(CircleShape)

        )
        Column {

            Box(Modifier.padding(start = 25.dp,top=5.dp)){
                Text(text=name, fontWeight = FontWeight.SemiBold)
            }
            Box(Modifier.padding(start=25.dp, top = 5.dp)){
                Text(text=description, fontWeight = FontWeight.SemiBold)
            }
        }



    }
}


