package com.paulomoura.pokedexkmm.android.presentation.pokemondetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.paulomoura.pokedexkmm.android.presentation.common.composable.debugPlaceholder
import com.paulomoura.pokedexkmm.android.presentation.pokemondetail.composable.TYPE.*
import com.paulomoura.pokedexkmm.android.presentation.ui.resources.EVOLUTIONS
import com.paulomoura.pokedexkmm.android.presentation.ui.resources.NO_EVOLUTION
import com.paulomoura.pokedexkmm.android.presentation.ui.resources.TYPE
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.BlueViolet
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.Brown
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.BurlyWood
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.CornflowerBlue
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.DarkKhaki
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.DarkSlateBlue
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.DarkWhite
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.DeepPink
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.DeepSkyBlue
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.Gold
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.Green
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.HotPink
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.LightSlateGray
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.Purple
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.Red
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.SlateGray
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.Turquoise
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.YellowGreen
import com.paulomoura.pokedexkmm.common.extension.toPokemonNumber
import com.paulomoura.pokedexkmm.domain.model.Pokemon

@Composable
fun DetailPokemon(pokemon: Pokemon?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        pokemon?.let { pokemon ->
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                item {
                    Text(
                        text = pokemon.name,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = pokemon.number.toPokemonNumber(),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(288.dp),
                        placeholder = debugPlaceholder(com.paulomoura.pokedexkmm.android.R.mipmap.pokemon_placeholder)
                    )
                    Text(
                        text = pokemon.description,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    Text(
                        text = TYPE,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    LazyRow {
                        items(pokemon.types) { type ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .size(128.dp, 40.dp)
                                    .background(
                                        color = getTypeColor(type),
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = type.uppercase(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(32.dp))
                    Text(
                        text = EVOLUTIONS,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start
                    )
                    if (pokemon.evolutions.size == 1) {
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = NO_EVOLUTION,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
                items(pokemon.evolutions) { evolution ->
                    AsyncImage(
                        model = evolution.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(128.dp),
                        placeholder = debugPlaceholder(com.paulomoura.pokedexkmm.android.R.mipmap.pokemon_placeholder)
                    )
                    Text(
                        text = evolution.name,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = evolution.number.toPokemonNumber(),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

private fun getTypeColor(type: String): Color {
    return when (type.uppercase()) {
        GRASS.toString() -> Green
        POISON.toString() -> Purple
        FIRE.toString() -> Red
        FLYING.toString() -> CornflowerBlue
        WATER.toString() -> DeepSkyBlue
        BUG.toString() -> YellowGreen
        NORMAL.toString() -> LightSlateGray
        ELECTRIC.toString() -> Gold
        GROUND.toString() -> BurlyWood
        FAIRY.toString() -> HotPink
        FIGHTING.toString() -> Brown
        PSYCHIC.toString() -> DeepPink
        ROCK.toString() -> DarkKhaki
        STEEL.toString() -> SlateGray
        ICE.toString() -> Turquoise
        GHOST.toString() -> DarkSlateBlue
        DRAGON.toString() -> BlueViolet
        else -> DarkWhite
    }
}

private enum class TYPE { GRASS, POISON, FIRE, FLYING, WATER, BUG, NORMAL, ELECTRIC, GROUND, FAIRY, FIGHTING, PSYCHIC, ROCK, STEEL, ICE, GHOST, DRAGON }