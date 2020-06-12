package markosertic.ferit.com.mindorks.framework.myapplication

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var MAX_STREAMS: Int = 10

    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    var mSoundMap: HashMap<Int, Int> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setUpUi()
        this.loadSounds()
    }
    private fun setUpUi() {
        this.tomson.setOnClickListener(this)
        this.kolinda.setOnClickListener(this)
        this.mamic.setOnClickListener(this)
    }
    private fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        //zvukovi su malo losije kvalitete :(
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.mamic] = this.mSoundPool.load(this, R.raw.mamic, 1)
        this.mSoundMap[R.raw.tomson] = this.mSoundPool.load(this, R.raw.tomson, 1)
        this.mSoundMap[R.raw.kolinda] = this.mSoundPool.load(this, R.raw.kolinda, 1)
    }
    override fun onClick(v: View) {
        if (this.mLoaded == false) return
        when (v.getId()) {
            R.id.tomson -> playSound(R.raw.tomson)
            R.id.mamic -> playSound(R.raw.mamic)
            R.id.kolinda -> playSound(R.raw.kolinda)
        }
    }
    fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
}

