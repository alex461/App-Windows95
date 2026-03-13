package org.project.windows95.domain.model

import androidx.compose.ui.geometry.Offset

data class ModelFolders(
    val id:Int,
    val name: String = "New Folder",
    val position: Offset,
    val selected: Boolean = false


)
