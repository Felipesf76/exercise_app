package com.example.exercise_app.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercise_app.R

@Composable
fun TitleComponent (title: String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(
                top = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            style = TextStyle(
                color = colorResource(R.color.Secondary),
                fontWeight = FontWeight.Black,
                fontSize = 30.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            color = colorResource(R.color.Secondary),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}