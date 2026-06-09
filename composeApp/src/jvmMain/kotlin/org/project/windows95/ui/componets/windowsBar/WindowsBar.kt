package org.project.windows95.ui.componets.windowsBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.project.windows95.domain.model.ModelWindow
import org.project.windows95.ui.color.backgroundComponent
import org.project.windows95.ui.componets.InformationBar
import org.project.windows95.ui.componets.WindowsButton
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.winLogo

@Composable
fun WindowsBar(time: String, windowsList: List<ModelWindow>,onWindowsButtonSelected:() -> Unit , onWindowMinimized:(ModelWindow) -> Unit ) {

    Column {
        Box(Modifier.fillMaxWidth().height(1.dp).background(Color.White))

        Row(Modifier.fillMaxWidth().height(40.dp).background(backgroundComponent), verticalAlignment = Alignment.CenterVertically) {
            WindowsButton(
                modifier = Modifier.padding(start = 8.dp).height(36.dp).width(95.dp)
                    .padding(horizontal = 4.dp),
                onClick = { onWindowsButtonSelected() },
                content = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(38.dp),
                            painter = painterResource(Res.drawable.winLogo),
                            contentDescription = "inicio",
                            contentScale = ContentScale.FillWidth
                        )

                        Spacer(Modifier.width(4.dp))
                        Text(text = "Start", fontSize = 18.sp)
                    }


                })
            Row(Modifier.height(36.dp).weight(1f)) {
                windowsList.forEach { window ->
                    key(window.id){
                           WindowMinimizedItem(window,{
                               onWindowMinimized(window.copy(minimized = !window.minimized))

                           })

                    }


                }
            }

            InformationBar(time)
        }



    }

}