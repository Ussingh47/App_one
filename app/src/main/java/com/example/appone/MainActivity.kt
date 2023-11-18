package com.example.appone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.appone.data.Animal
import com.example.appone.data.FakeRepository
import com.example.appone.ui.theme.AppOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val animals = FakeRepository().loadDate()
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(text = "Cats and Dogs", style = MaterialTheme.typography.headlineSmall)
        }
        items(animals) { animal ->
            AnimalCard(animal = animal, onItemClick = {
                Log.d("Event","photo clicked")
            }, onFavClick = {
                Log.d("Event","Fav clicked")
            })
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimalCard(
    animal: Animal,
    onItemClick: () -> Unit = {},
    onFavClick: () -> Unit = {},
    modifier: Modifier = Modifier

    ) {
    Card(
        onClick = onItemClick,
        modifier = modifier

    ) {
        Box(

        ) {
            GlideImage(
                model = animal.image,
                loading = placeholder(ColorPainter(Color.LightGray)),
                contentDescription = animal.title,
                transition = CrossFade,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp)
            )
            Badge(
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.TopEnd)
            ) {
                Text(
                    text = animal.type.name,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )


                )
            }
        }
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = animal.title,
                modifier = Modifier.padding(16.dp)
                )
            IconButton(onClick = onFavClick,
                ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "FavoriteBorder",

                    )
            }
        }

    }
}





@Preview
@Composable
fun ScreenPreview() {
    AppOneTheme {
        HomeScreen()
    }
}

