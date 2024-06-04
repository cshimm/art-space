package com.example.artspace.data

import com.example.artspace.R

data class Pokemon(val name: String, val type: Types, val image: Int)
enum class Types { Grass, Fire, Water }
object PokemonProvider {
    val pokemon = arrayListOf(
        Pokemon("Squirtle", Types.Water, R.drawable.squirtle),
        Pokemon("Wartortle", Types.Water, R.drawable.wartortle),
        Pokemon("Blastoise", Types.Water, R.drawable.blastoise),

        Pokemon("Charmander", Types.Fire, R.drawable.charmander),
        Pokemon("Charmeleon", Types.Fire, R.drawable.charmeleon),
        Pokemon("Charizard", Types.Fire, R.drawable.charizard),

        Pokemon("Bulbasaur", Types.Grass, R.drawable.bulbasaur),
        Pokemon("Ivysaur", Types.Grass, R.drawable.ivysaur),
        Pokemon("Venusaur", Types.Grass, R.drawable.venusaur),
    )
}
