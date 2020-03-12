package com.bitvale.lavafab

import android.graphics.*
import androidx.annotation.ColorInt

/**
 * Created by Alexander Kolpakov on 27.08.2018
 */
class Drawer {

    private val parentCirclePath = Path()
    private val childCirclePath = Path()
    private val connectedBezierPath = Path()
    private val disconnectedBezierPath = Path()
    private val helperRect = Path()
    private val rectF = RectF()

    private var iconRect = RectF(0f, 0f, 0f, 0f)
    private val iconPaint = Paint().apply {
        isAntiAlias = true
        isFilterBitmap = true
    }

    private val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun setPaintColor(@ColorInt color: Int) {
        mainPaint.color = color
    }

    fun enableShadow() {
        mainPaint.setShadowLayer(7f, 0.0f, 3.0f, 0x64393939)
    }

    fun drawPath(canvas: Canvas, path: Path) {
        canvas.drawPath(path, mainPaint)
    }

    fun rewindParentCircle() = parentCirclePath.rewind()

    fun rewindChildCircle() = childCirclePath.rewind()

    fun addCircle(centerX: Float, centerY: Float, radius: Float, isParent: Boolean) {
        if (isParent) parentCirclePath.addCircle(centerX, centerY, radius, Path.Direction.CW)
        else childCirclePath.addCircle(centerX, centerY, radius, Path.Direction.CW)
    }

    fun drawParentCircle(canvas: Canvas?) = canvas?.drawPath(parentCirclePath, mainPaint)

    fun drawChildCircle(canvas: Canvas?) = canvas?.drawPath(childCirclePath, mainPaint)

    fun rewindHelpers() {
        connectedBezierPath.rewind()
        helperRect.rewind()
        disconnectedBezierPath.rewind()
        disconnectedBezierPath.reset()
    }

    fun addConnectedBezier(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, x4: Float, y4: Float) {
        connectedBezierPath.moveTo(x1, y1)
        connectedBezierPath.cubicTo(x2, y2, x3, y3, x4, y4)
    }

    fun addDisconnectedBezier(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, x4: Float, y4: Float) {
        disconnectedBezierPath.moveTo(x1, y1)
        disconnectedBezierPath.cubicTo(x2, y2, x3, y3, x4, y4)
    }

    fun drawConnectedHelpers(canvas: Canvas?, x1: Float, y1: Float, x2: Float, y2: Float) {
        rectF.set(x1, y1, x2, y2)
        helperRect.addRect(rectF, Path.Direction.CW)
        helperRect.op(connectedBezierPath, Path.Op.DIFFERENCE)
        canvas?.drawPath(helperRect, mainPaint)
    }

    fun drawDisconnectedHelpers(canvas: Canvas?) {
        canvas?.drawPath(disconnectedBezierPath, mainPaint)
    }

    fun drawIcon(canvas: Canvas?, icon: Bitmap?, centerX: Float, centerY: Float, radius: Float) {
        icon?.let {
            var widthOffset = icon.width / 2f
            var heightOffset = icon.height / 2f
            val maxIconSize = Math.max(icon.height, icon.width)
            if (maxIconSize > radius) {
                widthOffset = radius / 2
                heightOffset = widthOffset
            }
            iconRect.left = centerX - widthOffset
            iconRect.top = centerY - heightOffset
            iconRect.right = centerX + widthOffset
            iconRect.bottom = centerY + heightOffset
            canvas?.drawBitmap(icon, null, iconRect, iconPaint)
        }
    }
}