package com.example.technomusic.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.net.Uri
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.palette.graphics.Palette
import java.io.FileNotFoundException

object PaletteUtils {
    private fun getUpperSideDominantColor(bitmap: Bitmap): Int {
        val builder = Palette.Builder(bitmap)
            .setRegion(0, 0, bitmap.width, bitmap.height / 2)
        val defaultValue = 0xFFFFFF
        val p = builder.generate()
        return p.getDominantColor(defaultValue)
    }

    private fun getPrimaryDominantColor(bitmap: Bitmap): Int {
        val builder = Palette.Builder(bitmap)
            .setRegion(0, 0, bitmap.width, bitmap.height)
        val defaultValue = 0xFFFFFF
        val p = builder.generate()
        return p.getDominantColor(defaultValue)
    }

    private fun getLowerSideDominantColor(bitmap: Bitmap): Int {
        val defaultValue = 0xFFFFFF
        val builder = Palette.Builder(bitmap)
            .setRegion(0, bitmap.height / 2, bitmap.width, bitmap.height)
        return builder.generate().getDominantColor(defaultValue)
    }

    private fun getDominantGradient(bitmap: Bitmap, height: Int, width: Int): Bitmap {
        val topColor: Int = getUpperSideDominantColor(bitmap)
        val bottomColor: Int = getLowerSideDominantColor(bitmap)
        var topHex = Integer.toHexString(topColor)
        topHex = topHex.trim { it <= ' ' }
        topHex = "#$topHex"
        var bottomHex = Integer.toHexString(bottomColor)
        bottomHex = bottomHex.trim { it <= ' ' }
        bottomHex = "#$bottomHex"
        Log.e("color ", topHex)
        Log.e("color ", bottomHex)
        val colors = intArrayOf(Color.parseColor(topHex), Color.parseColor(bottomHex))
        //int[]colors = new int[]{ Color.GREEN,Color.BLACK};
        val mShader: Shader = LinearGradient(
            0f, 0f, (width / 2).toFloat(), (height / 2).toFloat(), colors,
            null, Shader.TileMode.CLAMP
        )
        val m = Matrix()
        m.setRotate(90F)
        mShader.setLocalMatrix(m)
        val paint = Paint()
        paint.shader = mShader
        val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        //canvas.drawRect(0,0,640,1137,paint);
        val matrix = Matrix()
        canvas.drawBitmap(resultBitmap, matrix, paint)
        return resultBitmap
    }

    fun getImagePrimaryColor(imageBitmap: Bitmap): String {
      //  val displayMetric = getDisplayMetrics(context)
//        val displayHeight = displayMetric.heightPixels
//        val displayWidth = displayMetric.widthPixels
        val upperColor = getPrimaryDominantColor(imageBitmap)
        var topHex = Integer.toHexString(upperColor)
        topHex = topHex.trim { it <= ' ' }
        return "#$topHex"
    }


    private fun getDisplayMetrics(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    fun getPrimaryColorGradientWithLowerBlack(uri: Uri, context: Context): Bitmap {
        val albumArtBitmap: Bitmap? = try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: FileNotFoundException) {
            null // No album art found
        }
        var finalBitmap: Bitmap? = null
        if (albumArtBitmap != null) {
            finalBitmap = albumArtBitmap
        } else {
            val imageId = Utility.getRandomImage()
            finalBitmap = BitmapFactory.decodeResource(context.resources, imageId)
        }
        val primaryColor: Int = getUpperSideDominantColor(finalBitmap!!) // Assuming you have a function to get the primary/dominant color
        val primaryHex = String.format("#%06X", 0xFFFFFF and primaryColor)

        val colors = intArrayOf(primaryColor, Color.BLACK)
        val mShader: Shader = LinearGradient(
            0f, 0f, (350 / 2).toFloat(), (450 / 2).toFloat(), colors,
            null, Shader.TileMode.CLAMP
        )

        val m = Matrix()
        m.setRotate(90F)
        mShader.setLocalMatrix(m)

        val paint = Paint()
        paint.shader = mShader

        val resultBitmap = Bitmap.createBitmap(350, 450, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)
        canvas.drawRect(0f, 0f, 450.toFloat(), 450.toFloat(), paint)

        return resultBitmap
    }

}