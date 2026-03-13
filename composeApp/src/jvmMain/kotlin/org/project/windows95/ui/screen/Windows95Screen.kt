package org.project.windows95.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import org.project.windows95.domain.model.ModelFolders
import org.project.windows95.ui.componets.DrawableFolder
import org.project.windows95.ui.componets.WindowsBar
import org.project.windows95.utils.clickableWithoutRipple
import org.project.windows95.viewModel.ViewModelWindows
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun Windows95Screen() {

    var currentTime by remember { mutableStateOf(SimpleDateFormat("hh:mm").format(Date())) }
    var showWindowsMenu by remember { mutableStateOf(false) }
    val folderViewModel = koinViewModel<ViewModelWindows>()

    val fakeFolder = ModelFolders(0,"Alex", Offset(x= 0f ,y =0f))
    var folders by remember { mutableStateOf(listOf<ModelFolders>(fakeFolder)) }

    LaunchedEffect(key1 = Unit){
        while (true){
            currentTime = SimpleDateFormat("hh:mm a").format(Date())
            delay(1000)
        }

    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Box(Modifier.fillMaxWidth().weight(1f).clickableWithoutRipple{
            showWindowsMenu = false
         folderViewModel.cleanFolders(folders,{ folders= it})

        }){
            folders.forEach { folder ->
                DrawableFolder(folder=folder,onMove={ newPosition ->

                    folders = folders.map{
                        if (it.id == folder.id) it.copy(position = newPosition) else it

                   }



                }, onTapFolder = { id ->
                    folders = folders.map { if (id == it.id) it.copy(selected = true) else it }

                }, onRename = { newName ->
                    folders = folders.map{
                        if (it.id == folder.id) it.copy(name = newName) else it

                    }

                }, onDoubleTapFolder = {

                    
                })


            }
            WindowsBarMenuScreen(showWindowsMenu= showWindowsMenu)
        }


        WindowsBar(currentTime, onWindowsButtonSelected = {showWindowsMenu = !showWindowsMenu})
    }

}