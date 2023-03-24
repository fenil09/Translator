package com.universe.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.universe.jetpack_compose.ui.theme.Jetpack_ComposeTheme

class ImageCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
                    ){

                val painter= painterResource(R.drawable.i1)
                val title="Welcome to the world of Iron Man"

                Box(modifier = Modifier.fillMaxWidth(1.5f) .padding(16.dp)){
                    CardImage(
                        painter = painter,
                        title=title)
                }

                Spacer(modifier = Modifier.height(25.dp))
                val newpainter= painterResource(R.drawable.image2)
                val newtitle="Thor world"

                Box(modifier = Modifier.fillMaxWidth(1f) .padding(16.dp)){

                    CardImage(
                        painter=newpainter,
                        title=newtitle
                    )
                }
            }


        }
    }


}

@Composable
fun CardImage(
    painter: Painter,
    title:String,
    modifier: Modifier=Modifier

){

    Card (
        shape= RoundedCornerShape(15.dp),
        elevation = 5.dp,)
    {

        Box(modifier.height(400.dp)){

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.fillMaxSize() .background(
                Brush.verticalGradient(
                    colors=listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 300f
                )
            ))

            Box(modifier = Modifier.fillMaxSize() .padding(12.dp), contentAlignment = Alignment.BottomStart){
                Text(title, style = TextStyle(color=Color.White, fontSize = 16.sp, fontStyle = FontStyle.Italic))
            }


        }
    }
}





