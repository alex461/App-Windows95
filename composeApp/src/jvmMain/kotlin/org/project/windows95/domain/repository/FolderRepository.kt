package org.project.windows95.domain.repository

import org.project.windows95.domain.model.ModelFolders

class FolderRepository {




    fun cleanFolders(folders: List<ModelFolders>):List<ModelFolders>{

        return  folders.map { it.copy(selected = false) }


    }






}