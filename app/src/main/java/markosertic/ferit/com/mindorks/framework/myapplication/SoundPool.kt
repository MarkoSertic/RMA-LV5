package markosertic.ferit.com.mindorks.framework.myapplication

import android.app.Application
import android.content.Context

class Soundpool : Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}