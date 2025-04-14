package com.example.exercise_app.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavHostController
import com.example.exercise_app.R
import com.example.exercise_app.data.utils.Screen
import com.example.exercise_app.views.components.ButtonRedirect

@Composable
fun BoxHomeScreen(navController: NavHostController){
    Column (
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ){
            TitleHomePage()
        }
        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text= stringResource(R.string.home_question),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                    )
                )
                ButtonRedirect(
                    text = stringResource(R.string.create_routine),
                    onClick = {
                        // falta página de crear una rutina
                    })
                ButtonRedirect(
                    text = stringResource(R.string.do_exercise),
                    onClick = {
                        navController.navigate(Screen.RoutineScreen.route)
                    })
            }
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

