package com.example.kanjiapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.kanjiapp.R

@Composable
fun KanjiApp(
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = "Kanji App",
            style = MaterialTheme.typography.headlineLarge
        )
        
        KanjiCard(
            kanjiRes = R.string.kanji_big,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}