package org.project.windows95.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import org.project.windows95.tools.SoundManager
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.img

@Composable
fun SplashScreen(onSoundFinished: () -> Unit) {

    val soundManager = remember { SoundManager() }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(Res.drawable.img),
        contentDescription = "Windows splash",
        contentScale = ContentScale.Crop
    )

    soundManager.playSplashSound { onSoundFinished() }

}