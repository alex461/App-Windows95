package org.project.windows95.di


import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.project.windows95.domain.repository.FolderRepository
import org.project.windows95.viewModel.ViewModelWindows

val repositoryModule = module {
    single{ FolderRepository()}

}

val viewModelsModule = module {
    viewModelOf(::ViewModelWindows)
}