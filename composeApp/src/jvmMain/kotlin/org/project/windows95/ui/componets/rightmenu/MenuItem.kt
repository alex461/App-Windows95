package org.project.windows95.ui.componets.rightmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource
import org.project.windows95.ui.color.backgroundComponent
import org.project.windows95.ui.color.disabledTextColor
import org.project.windows95.ui.color.windowsBlue
import org.project.windows95.utils.clickableWithoutRipple
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_arrow
import windows95.composeapp.generated.resources.winLogo
import java.awt.MenuItem

@Composable
fun MenuItem(text: String,
             enabled:Boolean= true,
             showSubMenu: Boolean = false,
             hovered:(Offset?) -> Unit,
             onClick:() -> Unit ={}){


    val interactionSource = remember{ MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val background = if(isHovered && enabled) windowsBlue else backgroundComponent
    val textColor = if (isHovered) Color.White else Color.Black

    var subMenuPosition by remember { mutableStateOf(Offset(0f,0f)) }

    LaunchedEffect(isHovered){   if(isHovered){
        hovered(subMenuPosition)
    }}

    Row(Modifier.hoverable(interactionSource).fillMaxWidth().padding( 4.dp)
        .background(background).padding(4.dp).clickableWithoutRipple{

        if (enabled){
        onClick()
        }
    }.onGloballyPositioned{ layoutCoordinates ->
        val position  = layoutCoordinates.positionInParent()
            subMenuPosition = Offset(x = position.x +layoutCoordinates.size.width.toFloat() ,y= position.y )



    })
    {

        Spacer(Modifier.width(20.dp))
        Text(text= text, color = if (enabled) textColor else disabledTextColor, style = TextStyle(lineHeight = 0.sp) )
        Spacer(Modifier.weight(1f))
        if (showSubMenu){
            Icon(modifier = Modifier.padding(horizontal = 4.dp).size(12.dp),
                painter = painterResource(Res.drawable.ic_arrow),
                contentDescription = "",
                tint = Color.Black)
        }
    }
}