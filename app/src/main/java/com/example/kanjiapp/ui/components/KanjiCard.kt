package com.example.kanjiapp.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kanjiapp.R

@Composable
fun KanjiCard(
    @StringRes kanjiRes : Int,
    modifier: Modifier = Modifier
){
    
    var rotated by remember { mutableStateOf(false) }
    
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500),
        label = "rotation"
    )

    val animateFront by animateFloatAsState(
        targetValue = if (rotated) 0f else 1f,
        animationSpec = tween(500),
        label = "animateFront"
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500),
        label = "animateBack"
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(136.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
            }
    ) {
        if (!rotated)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(id = kanjiRes),
                    fontSize = 88.sp,
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = animateFront
                        }
                )
            }
        else
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .graphicsLayer {
                            alpha = animateBack
                            rotationY = rotation
                        }
                ){
                    Image(
                        painter = painterResource(id = R.drawable.kanji_big_stroke_order),
                        contentDescription = "Kanji Stroke Image",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Row {
                            Text(
                                text = "Kun:",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = stringResource(id = R.string.hiragana_o_big) + stringResource(
                                    id = R.string.hiragana_o_big),
                                modifier = Modifier
                            )
                        }

                        Row {
                            Text(
                                text = "On:",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = stringResource(id = R.string.katakana_da_big) + stringResource(
                                    id = R.string.katakana_i_big),
                                modifier = Modifier
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row {
                            Text(
                                text = "Meaning:",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "large, big",
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
    }
}