package org.project.windows95.viewModel

import KafkaClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.project.windows95.domain.model.ModelFolders
import org.project.windows95.domain.repository.FolderRepository

class ViewModelWindows(val folderRepository: FolderRepository,private val kafkaClient: KafkaClient): ViewModel() {


    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()








    init {
        // 🔥 AL ARRANCAR EL VIEWMODEL, ABRIMOS EL GRIFO
        activarEscuchaDeKafka()
    }

    private fun activarEscuchaDeKafka() {
        // Ejecutamos en el scope de corrutinas para no bloquear el hilo principal de la UI
        viewModelScope.launch {

            println("🔑 Llamando a .collect() para activar el Flow...")

            // 🎯 AQUÍ SE ACTIVA TODO. Al llamar a .collect, el código de tu KafkaClient se despierta
            kafkaClient.escucharMensajesDeKafka().collect { textoQueLlego ->



                // Actualizamos nuestro estado con lo que llegó de Kafka
                _message.value = textoQueLlego

                println("🎯 ¡UI Enterada! Llegó: $textoQueLlego")
            }
        }
    }




    fun cleanFolders(folders: List<ModelFolders> , response:(List<ModelFolders>) -> Unit) {

        viewModelScope.launch(Dispatchers.IO) {
            response(folderRepository.cleanFolders(folders))


        }

    }




}