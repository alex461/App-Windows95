package org.project.windows95.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import org.project.windows95.domain.model.ModelFolders
import org.project.windows95.domain.model.ModelWindow
import org.project.windows95.ui.color.backgroundComponent
import org.project.windows95.ui.color.windowsBarTextBackground
import org.project.windows95.ui.color.windowsBlue
import org.project.windows95.ui.componets.DrawableFolder
import org.project.windows95.ui.componets.DrawableWindow
import org.project.windows95.ui.componets.rightmenu.RightClickMenu
import org.project.windows95.ui.componets.windowsBar.WindowsBar
import org.project.windows95.utils.clickableWithoutRipple
import org.project.windows95.utils.onRightClick
import org.project.windows95.viewModel.ViewModelWindows
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun Windows95Screen() {

    var currentTime by remember { mutableStateOf(SimpleDateFormat("hh:mm").format(Date())) }
    var showWindowsMenu by remember { mutableStateOf(false) }
    val folderViewModel = koinViewModel<ViewModelWindows>()

    val fakeFolder = ModelFolders(0,"Alex", Offset(x= 0f ,y =0f))
    val fakeFolder2 = ModelFolders(1,"carpeta", Offset(x= 0f ,y =80f))

    var folders by remember { mutableStateOf(listOf<ModelFolders>(fakeFolder,fakeFolder2)) }
    var windowsList by remember { mutableStateOf(listOf<ModelWindow>()) }

    var showRightMenu by remember { mutableStateOf(false) }
    var rightClickPosition by remember { mutableStateOf(IntOffset.Zero) }

    val kafkaMessage by folderViewModel.message.collectAsState()
    var textKafka by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit){
        while (true){
            currentTime = SimpleDateFormat("hh:mm a").format(Date())
            delay(1000)
        }

    }


    LaunchedEffect(kafkaMessage) {
        if (kafkaMessage.isNotEmpty()) {
            textKafka = kafkaMessage
            delay(5000) // 👈 Espera exactamente 5 segundos (5000 milisegundos)
            textKafka = "" // 👈 Se limpia el texto
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Box(Modifier.fillMaxWidth().weight(1f).clickableWithoutRipple{
            showWindowsMenu = false
            showRightMenu =false
         folderViewModel.cleanFolders(folders,{ folders= it})

        }.onRightClick{
            rightClickPosition = it
            showRightMenu = true


        }){




            folders.forEach { folder ->
                DrawableFolder(folder=folder,onMove={ newPosition ->

                    folders = folders.map{
                        if (it.id == folder.id) it.copy(position = newPosition) else it

                   }



                }, onTapFolder = { id ->
                    folders = folders.map { it.copy(selected = id == it.id)  }

                }, onRename = { newName ->
                    folders = folders.map{
                        if (it.id == folder.id) it.copy(name = newName) else it

                    }

                }, onDoubleTapFolder = { selectedFolder ->

                    if ( windowsList.any { it.id== selectedFolder.id }){

                    windowsList = windowsList.map { if(it.id == selectedFolder.id) it.copy(selected = true, minimized = false) else it }
                    }else{

                        val extraPosition = windowsList.size*10

                        val newWindow = ModelWindow(
                            id = selectedFolder.id,
                            title = selectedFolder.name,
                            selected = true,
                            position = Offset(100f + extraPosition,100f + extraPosition),
                        )
                        windowsList = windowsList + newWindow

                    }


                }   )


            }



            if (textKafka!="") {
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(75.dp)
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 16.dp, end = 16.dp)
                        .background(color = backgroundComponent)
                ) {
                    Text(
                        text = textKafka,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            WindowsBarMenuScreen(showWindowsMenu= showWindowsMenu)

            windowsList.filter { !it.minimized }.forEach { window ->
                DrawableWindow(
                    window = window,
                    onMove = { offset ->

                        windowsList = windowsList.map { if (it.id == window.id) it.copy(position = offset ) else it }

                    },
                    onClose = {
                        windowsList = windowsList.filter { it.id != window.id }


                    },
                    onMinimize = {
                        windowsList = windowsList.map { if (it.id == window.id) it.copy(minimized = true ) else it }

                    },
                    onExpand = {
                        windowsList = windowsList.map { if (it.id == window.id) it.copy(expander = !it.expander ) else it }

                    }
                    )

            }
            RightClickMenu(showMenu = showRightMenu , position = rightClickPosition, onDismissItem = {showRightMenu = false} )
        }


        WindowsBar(time=currentTime,windowsList=windowsList,
            onWindowsButtonSelected = {showWindowsMenu = !showWindowsMenu},
            onWindowMinimized = { window ->
                windowsList = windowsList.map { if (it.id == window.id) it.copy(minimized = window.minimized ) else it }}
            )
    }

}