package tam.pa.al_quransederhana.helper

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

class Helper(val context: Context) {
    val mediaPlayer = MediaPlayer()

    fun playAudio(url: String): Boolean{
        mediaPlayer.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
            start()}
        return checkMedia()
    }

    fun stopAudio(): Boolean{
        if (mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        return false
    }

    fun checkMedia(): Boolean{
        if (!mediaPlayer.isPlaying) return false
        else return true
    }
}