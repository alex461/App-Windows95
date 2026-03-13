package org.project.windows95.tools

import java.io.File
import java.net.URL
import javax.print.attribute.standard.Finishings
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineEvent

class SoundManager {

    fun playSplashSound(onSoundFinished: () -> Unit){

        val classLoader = this::class.java.classLoader
        val resourse: URL? = classLoader.getResource("splashsound.wav")

        if (resourse!=null){

        val file = File(resourse.toURI())
        val audioInputStream = AudioSystem.getAudioInputStream(file)
        val clip : Clip = AudioSystem.getClip()

            clip.addLineListener { event ->
                if (event.type == LineEvent.Type.STOP){
                    clip.stop()
                    onSoundFinished()
                }

            }

            clip.open(audioInputStream)
            clip.start()

    }else{

        onSoundFinished()

    }
    }
}