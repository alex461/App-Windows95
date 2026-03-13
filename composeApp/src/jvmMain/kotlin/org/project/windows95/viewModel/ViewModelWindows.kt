package org.project.windows95.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.project.windows95.domain.model.ModelFolders
import org.project.windows95.domain.repository.FolderRepository

class ViewModelWindows(val folderRepository: FolderRepository): ViewModel() {



    fun cleanFolders(folders: List<ModelFolders> , response:(List<ModelFolders>) -> Unit) {

        viewModelScope.launch(Dispatchers.IO) {
            response(folderRepository.cleanFolders(folders))


        }

    }


}