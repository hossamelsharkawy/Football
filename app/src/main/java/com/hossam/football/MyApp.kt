package com.hossam.football

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Coil.setImageLoader(
            ImageLoader.Builder(this)
                .components {
                    add(SvgDecoder.Factory())
                }
                /// .error(ic_loading)
                // .fallback(ic_loading)
                .allowHardware(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .networkCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .crossfade(800)
                .diskCache {
                    DiskCache.Builder()
                        .maxSizeBytes(9000_000_00L)
                        .maxSizeBytes(9000_000_00L)
                        .maxSizePercent(1.0)
                        .directory(this.applicationContext.cacheDir.resolve("image_cache"))
                        .build()
                }
                .build()
        )

    }
}