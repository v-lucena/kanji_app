package com.example.kanjiapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.kanjiapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanjiApp(
    modifier: Modifier = Modifier
){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Kanji App",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        }
    ){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
        ) {

            Spacer(modifier = Modifier.weight(1f))
            KanjiCard(
                kanjiStringRes = R.string.kanji_big,
                kanjiDrawableRes = R.drawable.kanji_big_stroke_order,
                modifier = Modifier
                    .weight(3f)
            )
            Spacer(modifier = Modifier.weight(2f))
        }
    }


}