package com.github.ojh.caldroidsample

import android.app.Application
import com.github.ojh.caldroidsample.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by ohjaehwan on 2016. 12. 12..
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSansKR-DemiLight.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}