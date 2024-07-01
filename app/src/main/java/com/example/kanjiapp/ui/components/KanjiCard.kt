package com.example.kanjiapp.ui.components

import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.example.kanjiapp.R

private const val CAMERA_DISTANCE_MODIFIER = 8
private const val MAX_ROTATION = 180f
private const val MINIMUM_ROTATION = 0f
private const val ROTATION_ANIMATION_DURATION = 500
private const val KANJI_FONT_SIZE = 256f

@Composable
fun KanjiCard(
    @StringRes kanjiStringRes : Int,
    @DrawableRes kanjiDrawableRes: Int,
    modifier: Modifier = Modifier
){
    
    var rotated by rememberSaveable { mutableStateOf(false) }
    
    val rotation by animateFloatAsState(
        targetValue = if (rotated) MAX_ROTATION else MINIMUM_ROTATION,
        animationSpec = tween(ROTATION_ANIMATION_DURATION),
        label = "rotation"
    )

    val animateFront by animateFloatAsState(
        targetValue = if (rotated) 0f else 1f,
        animationSpec = tween(ROTATION_ANIMATION_DURATION),
        label = "animateFront"
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(ROTATION_ANIMATION_DURATION),
        label = "animateBack"
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.kanji_card_height))
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = CAMERA_DISTANCE_MODIFIER * density
            }
            .clickable {
                rotated = !rotated
            }
    ) {
        if (!rotated)
            CardFront(kanjiStringRes, animateFront)
        else
            CardBack(kanjiDrawableRes, animateBack, rotation)
    }
}

@Composable
private fun CardBack(kanjiDrawableRes: Int, animateBack: Float, rotation: Float) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            .graphicsLayer {
                alpha = animateBack
                rotationY = rotation
            }
    ) {
        KanjiStrokeImage(
            kanjiDrawableRes = kanjiDrawableRes,
            modifier = Modifier
                .weight(2f)
        )

        KanjiInformation(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .weight(1f)
        )
    }
}

@Composable
private fun KanjiInformation(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        ){
            Text(
                text = stringResource(R.string.kun_reading_label),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Text(
                text = stringResource(id = R.string.hiragana_o_big) + stringResource(
                    id = R.string.hiragana_o_big
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Text(
                text = stringResource(R.string.on_reading_label),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Text(
                text = stringResource(id = R.string.katakana_da_big) + stringResource(
                    id = R.string.katakana_i_big
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            /*Row {
                Text(
                    text = stringResource(R.string.kun_reading_label),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small)))
                Text(
                    text = stringResource(id = R.string.hiragana_o_big) + stringResource(
                        id = R.string.hiragana_o_big
                    ),
                    modifier = Modifier
                )
            }

            Row {
                Text(
                    text = stringResource(R.string.on_reading_label),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small)))
                Text(
                    text = stringResource(id = R.string.katakana_da_big) + stringResource(
                        id = R.string.katakana_i_big
                    ),
                    modifier = Modifier
                )
            }*/
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        ){
            Text(
                text = stringResource(R.string.kanji_meaning_label),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Text(
                text = "large, big, some more text",
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            /*Row {
                Text(
                    text = stringResource(R.string.kanji_meaning_label),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.size_small)))
                Column {
                    Text(
                        text = "large, big, some more text",
                        modifier = Modifier
                    )
                }
            }*/
        }
    }
}

@Composable
private fun KanjiStrokeImage(
    kanjiDrawableRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = kanjiDrawableRes),
        contentDescription = stringResource(R.string.kanji_stroke_image_content_description),
        modifier = modifier
            .fillMaxSize()
    )
}

@Composable
private fun CardFront(kanjiRes: Int, animateFront: Float) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(id = kanjiRes),
            fontSize = TextUnit(KANJI_FONT_SIZE, TextUnitType.Sp),
            modifier = Modifier
                .graphicsLayer {
                    alpha = animateFront
                }
        )
    }
}