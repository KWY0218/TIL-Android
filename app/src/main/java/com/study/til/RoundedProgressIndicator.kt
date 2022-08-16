package com.study.til

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun RoundedProgressIndicator(
    modifier: Modifier,
    progress: Float,
    trackColor: Color,
    backgroundColor: Color
) {
    val animateNumber = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 600,
            easing = LinearOutSlowInEasing
        )
    )
    Canvas(modifier = modifier) {
        drawLine(
            color = backgroundColor,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f),
            strokeWidth = 50F
        )

//        val calculatedProgress = ((size.width / listSize) * count.value)

        drawLine(
            color = trackColor,
            strokeWidth = 50F,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = 0.0f, y = 0f),
            cap = StrokeCap.Round
        )
    }
}
