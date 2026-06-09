package org.project.windows95.ui.componets.rightmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.project.windows95.ui.componets.BackgroundComponent

@Composable
fun Submenu(offset: Offset) {

    BackgroundComponent(Modifier.width(170.dp).offset { IntOffset(offset.x.toInt(), offset.y.toInt()) } ) {

        Column(Modifier.padding(3.dp)){

            MenuItem(text = "Line up Icons", hovered = {} )
            MenuItem(text = "Line up Icons", hovered = {} )
            MenuItem(text = "Line up Icons", hovered = {} )
            MenuItem(text = "Line up Icons", hovered = {} )
            MenuItem(text = "Line up Icons", hovered = {} )


        }

    }

}