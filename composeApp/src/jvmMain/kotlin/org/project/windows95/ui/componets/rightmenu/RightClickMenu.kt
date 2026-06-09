package org.project.windows95.ui.componets.rightmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.project.windows95.ui.componets.BackgroundComponent


@Composable
fun RightClickMenu(showMenu: Boolean, position : IntOffset, onDismissItem: () -> Unit ){

    var submenuPosition : Offset? by remember { mutableStateOf(null) }

    AnimatedVisibility (showMenu, exit = ExitTransition.None){

        Popup(offset = position, onDismissRequest = {onDismissItem()}, alignment = Alignment.TopStart){
            BackgroundComponent(Modifier.width(170.dp)) {

                Column(Modifier.padding(3.dp)){
                    MenuItem(text = "Arrage Icons", showSubMenu = true, hovered = {position
                        -> submenuPosition =position})
                    MenuItem(text = "Line up Icons", hovered = {submenuPosition = null} )
                    MenuDivider()
                    MenuItem(text = "Paste ",enabled = false , hovered = {submenuPosition = null})
                    MenuItem(text = "Arrage Shortcut",enabled = false , hovered = {submenuPosition = null})
                    MenuItem(text = "Undo Copy" , hovered = {submenuPosition = null})
                    MenuDivider()
                    MenuItem(text = "New", showSubMenu = true,hovered = {position
                        -> submenuPosition =position})
                    MenuDivider()
                    MenuItem(text = "Propeties", onClick = {
                        onDismissItem()
                    }, hovered = {submenuPosition = null})
                }


                submenuPosition?.let {
                    Submenu(offset = it)

                }

            }

        }

    }


}