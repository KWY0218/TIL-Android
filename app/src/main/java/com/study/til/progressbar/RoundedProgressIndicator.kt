package com.study.til.progressbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
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
    Canvas(modifier = modifier.fillMaxWidth()) {
        drawLine(
            color = backgroundColor,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f),
            strokeWidth = 30f
        )

        val calculatedProgress = (progress * size.width)

        drawLine(
            color = trackColor,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = calculatedProgress, y = 0f),
            cap = StrokeCap.Round,
            strokeWidth = 30f
        )
    }
}
