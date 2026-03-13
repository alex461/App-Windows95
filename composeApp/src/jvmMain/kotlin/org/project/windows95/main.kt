package org.project.windows95

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import org.project.windows95.di.repositoryModule
import org.project.windows95.di.viewModelsModule

fun main() {

    startKoin {
        printLogger()
        modules(

            viewModelsModule,
            repositoryModule
        )
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "windows95",
            resizable = false
        ) {
            App()
        }


}
}










