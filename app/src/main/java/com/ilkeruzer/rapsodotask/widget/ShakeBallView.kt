package com.ilkeruzer.rapsodotask.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.View
import com.ilkeruzer.rapsodotask.R

class ShakeBallView(context: Context, attrs: AttributeSet?): View(context, attrs) {

    private lateinit var ballDraw: ShapeDrawable

    private val defaultBallWidth = resources.getDimensionPixelSize(R.dimen.shake_ball_default_with)
    private val defaultBallHeight = resources.getDimensionPixelSize(R.dimen.shake_ball_default_height)

    private var ballX = 0
    private var ballY = 0

    companion object {
        private const val BALL_DEFAULT_DIAMETER = 100
    }

    init {
        createBall()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        ballDraw.draw(canvas)
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


        setMeasuredDimension(width,height)



    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setBallScreenCenter()
        invalidate()
    }

    private fun setBallScreenCenter() {
        val r = BALL_DEFAULT_DIAMETER / 2
        ballX = (width / 2) - r
        ballY = (height / 2) - r

        ballDraw.setBounds(
            ballX,
            ballY,
            (ballX + BALL_DEFAULT_DIAMETER),
            (ballY + BALL_DEFAULT_DIAMETER)
        )
        invalidate()
    }


    private fun createBall() {
        ballDraw = ShapeDrawable(OvalShape())
        ballDraw.paint.color = -0x8b53dd
    }

    fun move(f: Float, g: Float) {

        ballX = ((ballX - f).toInt())
        ballY = ((ballY + g).toInt())


        //top x sınırları geçtiyse
        when {
            ballX > width - BALL_DEFAULT_DIAMETER -> {
                ballX = width - BALL_DEFAULT_DIAMETER - 1
            }
            ballX < 0 -> {
                ballX = 1
            }
            ballX == 0 -> {
                ballX = ballX
            }
        }

        //top y sınırlarını geçtiyse
        when {
            ballY > height - BALL_DEFAULT_DIAMETER -> {
                ballY = height - BALL_DEFAULT_DIAMETER - 1
            }
            ballY < 0 -> {
                ballY = 1
            }
            ballY == 0 -> {
                ballY = ballY
            }
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