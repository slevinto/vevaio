package com.slevinto.vevaio

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class CircleImageView : AppCompatImageView {
    private var borderColor = 0
    private var borderWidth = 0f

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        parseAttributes(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        parseAttributes(attrs)
    }

    override fun onDraw(canvas: Canvas) {
        val drawable = drawable ?: return
        if (width == 0 || height == 0) {
            return
        }
        val b = (drawable as BitmapDrawable).bitmap
        if (b == null || b.isRecycled) return
        val bitmap = b.copy(Bitmap.Config.ARGB_8888, true)
        val w = width
        val h = height
        val imgRadius = w - 2 * borderWidth.toInt()
        val paint = Paint()
        paint.setAntiAlias(true)
        paint.setColor(borderColor)
        canvas.drawCircle(
            (this.width / 2).toFloat(),
            (this.height / 2).toFloat(),
            (this.width / 2).toFloat(), paint
        )
        val roundBitmap = getRoundedCroppedBitmap(bitmap, imgRadius)
        canvas.drawBitmap(roundBitmap, borderWidth, borderWidth, null)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, com.slevinto.vevaio.R.styleable.CircleImageView)
        borderColor =
            ta.getColor(com.slevinto.vevaio.R.styleable.CircleImageView_border_color, 0xffffff) // default color white
        borderWidth = ta.getDimension(com.slevinto.vevaio.R.styleable.CircleImageView_border_width, 1.0f)
    }

    companion object {
        fun getRoundedCroppedBitmap(bitmap: Bitmap, radius: Int): Bitmap {
            val finalBitmap: Bitmap = if (bitmap.width != radius || bitmap.height != radius) Bitmap.createScaledBitmap(
                    bitmap, radius, radius,
                    false
                ) else bitmap
            val output = Bitmap.createBitmap(
                finalBitmap.width,
                finalBitmap.height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            val paint = Paint()
            var rect = Rect(
                0, 0, finalBitmap.width,
                finalBitmap.height
            )
            var rectF = RectF(
                0F, 0F, finalBitmap.width.toFloat(),
                finalBitmap.height.toFloat()
            )
            paint.isAntiAlias = true
            paint.isFilterBitmap = true
            paint.isDither = true
            canvas.drawARGB(0, 0, 0, 0)
            canvas.drawRoundRect(rectF, 64f, 64f, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(finalBitmap, rect, rect, paint)
            return output
        }
    }
}