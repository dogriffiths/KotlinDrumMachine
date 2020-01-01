import kotlinx.coroutines.*
import java.io.File
import javax.sound.sampled.AudioSystem

fun main() {
    runBlocking {
        launch { playBeats("x-x-x-x-x-x-x-x-", "toms.aiff") }
        launch { playBeats("x-x-x---x-x-x---", "high_hat.aiff") }
        launch { playBeats("----------------x----", "crash_cymbal.aiff") }
    }
}

suspend fun playBeats(beats: String, file: String) {
    println("Playing " + file + " in thread " + Thread.currentThread())
    val parts = beats.split("x")
        var count = 0
        for (part in parts) {
            count += part.length + 1
            if (part == "") {
                playSound(file)
            } else {
                delay(100 * (part.length + 1L))
                if (count < beats.length) {
                    playSound(file)
                }
            }
        }
}

fun playSound(file: String) {
    val clip = AudioSystem.getClip()

    val audioInputStream = AudioSystem.getAudioInputStream(
        File(
            file
        )
    )
    clip.open(audioInputStream)
    clip.start()
}