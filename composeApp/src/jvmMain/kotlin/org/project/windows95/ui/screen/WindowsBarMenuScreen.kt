package org.project.windows95.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.project.windows95.ui.componets.BackgroundComponent
import org.project.windows95.ui.componets.windowsBar.WindowsMenu
import org.project.windows95.ui.componets.windowsBar.WindowsMenuItem
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_programs


@Composable
fun WindowsBarMenuScreen(showWindowsMenu:Boolean){

    var subBarMenu by remember { mutableStateOf<Float?>(null) }


    if (showWindowsMenu){
        Column {
            Spacer(Modifier.weight(1f))
            Row {
                WindowsMenu { subBarMenu = it }
                subBarMenu?.let {



                 BackgroundComponent(Modifier.offset(y = it.dp).width(150.dp)) {



                        Column {
                            WindowsMenuItem(
                                text ="Accesories", painter = painterResource(Res.drawable.ic_programs), isSubMenu = true){}
                            WindowsMenuItem(
                                text ="Accesories", painter = painterResource(Res.drawable.ic_programs), isSubMenu = true){}

                            WindowsMenuItem(
                                text ="Accesories", painter = painterResource(Res.drawable.ic_programs), isSubMenu = true){}
                            WindowsMenuItem(
                                text ="Accesories", painter = painterResource(Res.drawable.ic_programs), isSubMenu = true){}
                            WindowsMenuItem(
                                text ="Accesories", painter = painterResource(Res.drawable.ic_programs), isSubMenu = true){}
                        }
                    }
                }




                }


            }





    }





}
