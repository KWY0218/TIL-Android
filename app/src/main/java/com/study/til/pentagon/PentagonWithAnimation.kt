package com.study.til.pentagon

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
@Preview(showBackground = true)
private fun AniPentagon() {
    PentagonWithAnimation(
        radiusList = listOf(350.0f, 350.0f, 350.0f, 350.0f, 350.0f)
    )
}

enum class AniState {
    START, END
}

@Composable
fun PentagonWithAnimation(
    radiusList: List<Float>
) {
    val animationTargetState = remember { mutableStateOf(AniState.START) }
    val transition = updateTransition(
        targetState = animationTargetState.value,
        label = ""
    )
    val radiusAnimationSpec = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = ""
    ) { if (it == AniState.START) 0f else 1f }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(drawPentagonPath(radiusList, radiusAnimationSpec)),
                paint = Paint().apply {
                    color = Color.Magenta
                    pathEffect = PathEffect.cornerPathEffect(15.0.dp.toPx())
                }
            )
        }
        animationTargetState.value = AniState.END
    }
}

private fun DrawScope.drawPentagonPath(
    radiusList: List<Float>,
    radiusAnimationSpec: State<Float>
): Path {
    val size = this.size.center
    val angle = 2.0 * Math.PI / 5.0f
    val radiusPxList = radiusList.onEach { radius -> radius.dp.toPx() }
    return Path().apply {
        val currentAngle = -0.5 * Math.PI
        reset()
        moveTo(
            size.x + (radiusPxList[0] * cos(currentAngle)).toFloat()
                .times(radiusAnimationSpec.value),
            size.y + (radiusPxList[0] * sin(currentAngle)).toFloat()
                .times(radiusAnimationSpec.value)
        )
        for (i in 1 until 5) {
            lineTo(
                size.x + (radiusPxList[i] * cos(currentAngle + angle * i))
                    .toFloat()
                    .times(radiusAnimationSpec.value),
                size.y + (radiusPxList[i] * sin(currentAngle + angle * i))
                    .toFloat()
                    .times(radiusAnimationSpec.value)
            )
        }
        close()
    }
}
