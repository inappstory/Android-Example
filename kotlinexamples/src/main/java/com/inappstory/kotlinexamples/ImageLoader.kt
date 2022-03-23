package com.inappstory.kotlinexamples

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.inappstory.sdk.stories.utils.Sizes
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

object ImageLoader {
    fun decodeFile(f: File): Bitmap? {
        try {
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            BitmapFactory.decodeStream(FileInputStream(f), null, o)
            val REQUIRED_SIZE: Int = Sizes.dpToPxExt(800)
            var width_tmp = o.outWidth
            var height_tmp = o.outHeight
            var scale = 1
            while (width_tmp / 2 >= REQUIRED_SIZE && height_tmp / 2 >= REQUIRED_SIZE) {
                width_tmp /= 2
                height_tmp /= 2
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            val fileInputStream = FileInputStream(f)
            val bitmap = BitmapFactory.decodeStream(fileInputStream, null, o2)
            fileInputStream.close()
            return bitmap
        } catch (e: Exception) {
        }
        return null
    }
}