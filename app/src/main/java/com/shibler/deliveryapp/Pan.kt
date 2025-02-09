package com.shibler.deliveryapp

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Pan() {

    val transition = rememberInfiniteTransition(label = "")

    val adjustPositionX = 0
    val adjustPositionY = 0


    val left = 45f + adjustPositionX
    val top = 45f+ adjustPositionY
    val right = 675f+ adjustPositionX
    val bottom = 135f+ adjustPositionY

    val radius = 90f

    val panAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1700
                18f at 300 using CubicBezierEasing(0f, 0.65f, 0.34f, 1f)
                0f at 1500 using CubicBezierEasing(0.6f, 0.0f, 0.8f, 0.4f)
                0f at 1700 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )


    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        //.border(1.dp, Color.Black)
        .graphicsLayer(
            transformOrigin = TransformOrigin(
                pivotFractionX = 0.8f,
                pivotFractionY = 0.45f,
            ),
            rotationZ = panAnimation)
    ) {
        val path = Path().apply {
            moveTo(left, top)

            arcTo(
                rect = Rect(left = left, top = bottom - 2 * radius, right = left + 2 * radius,bottom = bottom),
                startAngleDegrees = 180f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )


            arcTo(
                rect = Rect(right - 2 * radius, bottom - 2 * radius, right, bottom),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )

            //close()
        }
        drawPath(path, color = Color.Black)

        drawRoundRect(
            color = Color.Black,
            topLeft = Offset(right + 20f, top),
            size = Size(300f, 50f),
            cornerRadius = CornerRadius(30f, 30f)
        )
    }

}