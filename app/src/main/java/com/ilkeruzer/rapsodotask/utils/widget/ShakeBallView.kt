package com.ilkeruzer.rapsodotask.utils.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ilkeruzer.rapsodotask.R
import kotlin.math.*

class ShakeBallView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var ballDraw: ShapeDrawable

    private val defaultBallWidth = resources.getDimensionPixelSize(R.dimen.shake_ball_default_with)
    private val defaultBallHeight =
        resources.getDimensionPixelSize(R.dimen.shake_ball_default_height)

    private var ballX = 0
    private var ballY = 0
    private val rShakeBall = BALL_DEFAULT_DIAMETER / 2
    private var circleRectF = RectF()

    private val ovalPaint = Paint()


    companion object {
        private const val BALL_DEFAULT_DIAMETER = 100
        private const val OVAL_STROKE_WIDTH = 5F
        private const val SHAKE_BALL_VIEW_BIG_CIRCLE_DIAMETER = 400F
    }

    init {
        initBallPaint()
        initOvalPaint()
    }

    private fun initOvalPaint() {
        ovalPaint.style = Paint.Style.STROKE
        ovalPaint.strokeWidth = OVAL_STROKE_WIDTH
        ovalPaint.color = Color.BLACK
    }

    private fun initBallPaint() {
        ballDraw = ShapeDrawable(OvalShape())
        ballDraw.paint.color = -0x8b53dd
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        ballDraw.draw(canvas)

        canvas.drawOval(circleRectF, ovalPaint);
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> defaultBallWidth
            MeasureSpec.UNSPECIFIED -> defaultBallWidth
            else -> defaultBallWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> defaultBallHeight
            MeasureSpec.UNSPECIFIED -> defaultBallHeight
            else -> defaultBallHeight
        }


        setMeasuredDimension(width, height)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setBallScreenCenter()
        initCircleRect()
        invalidate()
    }

    private fun initCircleRect() {
        val height: Int = height / 2
        val width: Int = width / 2

        circleRectF.set(
            width - SHAKE_BALL_VIEW_BIG_CIRCLE_DIAMETER,
            height - SHAKE_BALL_VIEW_BIG_CIRCLE_DIAMETER,
            width + SHAKE_BALL_VIEW_BIG_CIRCLE_DIAMETER,
            height + SHAKE_BALL_VIEW_BIG_CIRCLE_DIAMETER
        )
    }

    private fun setBallScreenCenter() {
        ballX = (width / 2) - rShakeBall
        ballY = (height / 2) - rShakeBall

        ballDraw.setBounds(
            ballX,
            ballY,
            (ballX + BALL_DEFAULT_DIAMETER),
            (ballY + BALL_DEFAULT_DIAMETER)
        )
        invalidate()
    }

    fun move(f: Float, g: Float) {

        var oldX = ballX
        var oldY = ballY

        ballX = ((ballX - f).toInt())
        ballY = ((ballY + g).toInt())

        Log.d("coordinates old ", "${ballX - (width / 2) + rShakeBall} ${ballY - (height / 2) + rShakeBall}")

        val r = (SHAKE_BALL_VIEW_BIG_CIRCLE_DIAMETER - rShakeBall).toDouble()
        val r2 = r.pow(2.0)
        val x2 = ((ballX - (width / 2) + rShakeBall).toDouble()).pow(2.0)
        val y2 = ((ballY - (height / 2) + rShakeBall).toDouble()).pow(2.0)

        if ((x2 + y2) > r2) {
            if (ballX - (width / 2) + rShakeBall > 0) {
                oldX -= 1
            } else {
                oldX += 1
            }

            if (ballY - (height / 2) + rShakeBall > 0) {
                oldY -= 1
            } else
                oldY += 1

            ballY = oldY
            ballX = oldX
        }

        ballDraw.setBounds(
            ballX,
            ballY,
            (ballX + BALL_DEFAULT_DIAMETER),
            (ballY + BALL_DEFAULT_DIAMETER)
        )


        invalidate()
    }


}