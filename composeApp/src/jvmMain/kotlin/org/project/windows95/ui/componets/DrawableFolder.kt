package org.project.windows95.ui.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.project.windows95.domain.model.ModelFolders
import org.project.windows95.ui.color.windowsBlue
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_folder


@Composable
fun DrawableFolder(folder: ModelFolders, onMove:(Offset)-> Unit,
                    onTapFolder:(Int) -> Unit ,
                   onRename:(String)-> Unit,
                   onDoubleTapFolder:(ModelFolders) -> Unit) {

    var offSet by remember { mutableStateOf(folder.position) }
    val textColor = if(folder.selected) White else Black
    var newName by remember { mutableStateOf(folder.name) }
    var isEditing by remember { mutableStateOf(false) }
    var lastClickTime by remember { mutableStateOf(0L) }

    val focusRequest = remember { FocusRequester() }

    Box(
        Modifier.offset(x = folder.position.x.dp,y = folder.position.y.dp)
            .size(75.dp)
            .pointerInput(Unit){
                detectTransformGestures { _,pan,_,_ ->
                    offSet = offSet.copy(x = offSet.x + pan.x , y = offSet.y + pan.y )
                    onMove(offSet)

                }

            }.pointerInput(Unit){
                detectTapGestures (
                    onTap = {
                        onTapFolder(folder.id)
                      val currentTime = System.currentTimeMillis()
                        val timeSinceLastClick = currentTime - lastClickTime
                        if (timeSinceLastClick in 300 ..  800){
                            isEditing = true
                        }
                    lastClickTime = currentTime
                            }

                    ,
                    onPress = {
                        onTapFolder(folder.id)

                    },
                    onDoubleTap = {
                        onDoubleTapFolder(folder)

                    }
                )


            }
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Box(modifier = Modifier.weight(1f)){
            Image(modifier = Modifier.fillMaxSize(), painter = painterResource(Res.drawable.ic_folder),
                contentDescription = "folder"
            )
            if (folder.selected){
                Icon(modifier = Modifier.fillMaxSize(), painter = painterResource(Res.drawable.ic_folder),
                    contentDescription = "folder",
                    tint = windowsBlue.copy(alpha = 0.4f)
                )
            }

            }

            if (isEditing){

              BasicTextField(modifier = Modifier.focusRequester(focusRequest), value = newName, onValueChange = {newName = it})

                LaunchedEffect(Unit){

                    focusRequest.requestFocus()
                }

            }else {
                Text(text = folder.name, color = textColor, modifier = Modifier.background(if (folder.selected) windowsBlue else Color.Transparent))
            }

        }

      if (isEditing){
          Box(Modifier.fillMaxSize().pointerInput(Unit){
              detectTapGestures(
                  onTap = {
                      isEditing = false
                      onRename(newName)
                  })

          })
      }


    }

}