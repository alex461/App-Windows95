package org.project.windows95.ui.componets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.project.windows95.domain.model.ModelWindow
import org.project.windows95.ui.componets.windowsToolbar.WindowToolbar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawableWindow (window: ModelWindow,onMove:(Offset) -> Unit, onClose:() -> Unit ,onMinimize:() -> Unit,onExpand:() -> Unit){

    var currentOffset by remember { mutableStateOf(window.position) }
    val density = LocalDensity.current

    var prevPosition by remember { mutableStateOf(window.position) }

    BoxWithConstraints {

        val containerWithPx =  with(density){ maxWidth.toPx()}
        val containerHeightPx =  with(density){ maxHeight.toPx()}

        val windowWithPx = if (window.expander) containerWithPx else with(density){window.size.width.toPx()}
        val windowHeightPx = if (window.expander) containerHeightPx else  with(density){window.size.height.toPx()}

        LaunchedEffect(window.expander){
            currentOffset = if (window.expander){
                Offset(0f,0f)
            } else {
                prevPosition
            }
        }


        BackgroundComponent(Modifier.then(
            if (window.expander) Modifier.fillMaxSize() else Modifier.size(window.size) ).offset{
        IntOffset(currentOffset.x.toInt(),currentOffset.y.toInt())
    }.onDrag(
        matcher = PointerMatcher.mouse(PointerButton.Primary),
        onDrag = { offset ->

            if (!window.expander){

            val newX = (currentOffset.x + offset.x).coerceIn(0f,containerWithPx -windowWithPx)
            val newY = (currentOffset.y + offset.y).coerceIn(0f,containerHeightPx -windowHeightPx)
            val newOffset = Offset(newX,newY)
            currentOffset = newOffset
            prevPosition = newOffset
            onMove(newOffset)

        }}


    ))

    {
        Column {
            WindowToolbar(modifier = Modifier.padding(4.dp),
                title = window.title,
                selected = window.selected,
                onMinimize = {onMinimize()},
                onExpand = {onExpand()},
                onClose = { onClose()})
            Row {
                Spacer(Modifier.width(10.dp))
                Text("File")
                Spacer(Modifier.width(10.dp))
                Text("Edit")
                Spacer(Modifier.width(10.dp))
                Text("View")
                Spacer(Modifier.width(10.dp))
                Text("Help")

            }

            BackgroundComponent(
                modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 4.dp),
                selected = true
            ) {
                Box(Modifier.fillMaxSize().background(Color.White))
            }


        }


    }

}}