package com.universe.compose_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
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
import androidx.compose.ui.unit.sp
import com.universe.compose_1.ui.theme.Compose_1Theme

class LazyColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn(content = {
                items(getCategoryList()){
                    LazyView(it.imgid,it.title,it.subtitle)
                }
            })
        }
    }
}



@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun DefaultPreview2() {
   LazyColumn(content = {
       items(getCategoryList()){
           LazyView(it.imgid,it.title,it.subtitle)
       }
   })
}

@Composable
fun LazyView(imgid:Int,title:String,subtitle:String){

    Card (elevation = 8.dp, modifier = Modifier.padding(8.dp)){
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically){
            Image(
                painterResource(imgid),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(85.dp)
                    .clip(CircleShape)
            )
            Column(){
                Box(modifier = Modifier.padding(start = 7.dp,top=7.dp)){
                    Text(text=title, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                Box(modifier = Modifier.padding(start = 7.dp, top = 7.dp)){
                    Text(text=subtitle, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }


    }
}

data class Category(val imgid:Int,val title: String,val subtitle: String)
fun getCategoryList():MutableList<Category>{
    val list= mutableListOf<Category>()
    list.add(Category(imgid = R.drawable.ironman,"IronMan","This is ironman"))
    list.add(Category(imgid = R.drawable.thor,"Thor,","welcome to asgard"))
    list.add(Category(imgid = R.drawable.spiderman,"Spiderman","I love New york"))
    list.add(Category(imgid = R.drawable.strange,"Dr Strange","Master of Mystic Arts"))
    list.add(Category(imgid = R.drawable.ironman,"IronMan","This is ironman"))
    list.add(Category(imgid = R.drawable.thor,"Thor,","welcome to asgard"))
    list.add(Category(imgid = R.drawable.spiderman,"Spiderman","I love New york"))
    list.add(Category(imgid = R.drawable.strange,"Dr Strange","Master of Mystic Arts"))

    return list
}