package org.project.windows95.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


data class ModelWindow (
    val id: Int,
    val title : String,
    val position: Offset,
    val minimized: Boolean=false,
    val expander: Boolean=false,
    val selected: Boolean= false,
    val size: DpSize = DpSize(400.dp, 400.dp)
)