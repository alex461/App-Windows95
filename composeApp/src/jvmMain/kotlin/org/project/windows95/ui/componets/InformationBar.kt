package org.project.windows95.ui.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InformationBar(time: String) {




    Row(Modifier.padding(4.dp).fillMaxHeight()
        .border(BorderStroke(1.5.dp,Color.Gray)) ,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = time, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))

    }
}