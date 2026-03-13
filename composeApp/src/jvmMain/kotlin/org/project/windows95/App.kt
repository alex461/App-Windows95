package org.project.windows95


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.project.windows95.ui.color.background
import org.project.windows95.ui.font.Windows95Typography
import org.project.windows95.ui.screen.SplashScreen
import org.project.windows95.ui.screen.Windows95Screen


@Composable
@Preview
fun App() {
    MaterialTheme(typography = Windows95Typography()) {

        var initializer : Boolean by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize().background(background)){

            if (initializer){
                SplashScreen {  initializer= false}
            }else{
                Windows95Screen()
            }





        }



    }
}