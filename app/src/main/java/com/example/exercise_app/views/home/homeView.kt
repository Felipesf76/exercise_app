package com.example.exercise_app.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercise_app.R
import com.example.exercise_app.views.components.ButtonRedirect

@Composable
fun BoxHomeScreen(innerPadding: PaddingValues){
    Column (
        modifier = Modifier
            .padding(innerPadding)
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(width = 1.dp, color = Color.Black),
            contentAlignment = Alignment.Center
        ){
            TitleHomePage()
        }
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(width = 1.dp, color = Color.Black)
        ){
            Text(
                text= stringResource(R.string.home_question),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                )
            )
            ButtonRedirect(onClick = {
                // Aquí puedes agregar la lógica para crear una rutina
            })
        }
    }
}

@Composable
fun TitleHomePage(){
    Column (
        modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text= stringResource(R.string.title_home),
            style = TextStyle(
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Black
            )
        )
        Text(text= stringResource(R.string.welcome),
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        )
    }
}

