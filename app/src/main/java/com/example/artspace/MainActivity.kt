package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.data.Pokemon
import com.example.artspace.data.PokemonProvider
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    val pokemon = PokemonProvider.pokemon
    var current by remember { mutableIntStateOf(0) }
    val currentPokemon = pokemon[current]
    val likedPokemon = remember { mutableStateMapOf<Pokemon, Boolean>() }

    fun handleNext() {
        current = if (current == pokemon.size - 1) 0 else current + 1
    }

    fun handlePrev() {
        current = if (current == 0) pokemon.size - 1 else current - 1
    }

    fun handleLikeClicked(liked: Boolean) {
        likedPokemon[currentPokemon] = liked
    }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            // Image
            Surface(
                modifier = modifier
                    .align(Alignment.Center)
                    .offset(y = (-70).dp)
                    .padding(16.dp)
                    .size(230.dp, 330.dp),
                color = MaterialTheme.colorScheme.surface, // Surface color
                shadowElevation = 16.dp, // Shadow elevation for 3D effect
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = currentPokemon.image),
                        contentDescription = "pokemon",
                        modifier = modifier
                            .align(Alignment.Center)
                            .size(200.dp)
                    )
                }
            }
            // Artist details
            Row(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-150).dp)
            ) {
                Column {

                    Box(
                        modifier = modifier
                            .background(Color.LightGray)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Row {
                                Text(text = currentPokemon.name)
                            }
                            Row {
                                Text(text = "Type: ${currentPokemon.type}")
                            }
                        }
                    }
                }
                Column(modifier = modifier.align(Alignment.CenterVertically)) {
                    if (likedPokemon[currentPokemon] == true) {
                        IconButton( onClick = { handleLikeClicked(false) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_thumb_up_24),
                                contentDescription = "unlike"
                            )
                        }
                    } else {
                        IconButton(onClick = { handleLikeClicked(true) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_thumb_up_off_alt_24),
                                contentDescription = "like"
                            )
                        }
                    }
                }
            }
            //buttons
            Row(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-15).dp)
            ) {
                Column {
                    Button(onClick = { handlePrev() }) {
                        Text(text = "Previous")
                    }
                }
                Column() {
                    Button(onClick = { handleNext() }) {
                        Text(text = "Next")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}