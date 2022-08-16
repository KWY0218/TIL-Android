package com.study.til

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen() {
    val tempRules = mutableListOf(
        Pair(remember { mutableStateOf(false) }, "규칙1"),
        Pair(remember { mutableStateOf(false) }, "규칙2"),
        Pair(remember { mutableStateOf(false) }, "규칙3"),
        Pair(remember { mutableStateOf(false) }, "규칙4"),
        Pair(remember { mutableStateOf(false) }, "규칙5"),
        Pair(remember { mutableStateOf(false) }, "규칙6")
    )
    val increaseDegree = (1.0 / tempRules.size).toFloat()
    val progressing = remember { mutableStateOf(0.0f) }
    val count = remember { mutableStateOf(0) }

    LazyColumn(Modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        item {
            ProgressBarWithAnimation(progressing, count = count, size = tempRules.size)
            Spacer(modifier = Modifier.size(20.dp))
        }
        items(tempRules) { rule ->
            CheckBoxItem(
                rule = rule,
                increaseDegree = increaseDegree,
                progressing = progressing,
                count = count
            )
        }
    }
}

@Composable
fun ProgressBarWithAnimation(
    progressing: MutableState<Float>,
    count: MutableState<Int>,
    size: Int
) {
    val divideAni by animateFloatAsState(
        targetValue = progressing.value,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
    )
    Column(Modifier.fillMaxWidth()) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val imgAni by animateDpAsState(
                targetValue = (maxWidth / size) * count.value - 24.dp,
                animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
            )
            Log.d("sadffas", "$imgAni")
            Image(
                modifier = Modifier.absoluteOffset(x = imgAni),
                painter = painterResource(id = R.drawable.ic_run),
                contentDescription = ""
            )
        }
        LinearProgressIndicator(
            progress = divideAni,
            color = Color.Blue,
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun CheckBoxItem(
    rule: Pair<MutableState<Boolean>, String>,
    increaseDegree: Float,
    progressing: MutableState<Float>,
    count: MutableState<Int>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = rule.first.value,
            onCheckedChange = {
                if (rule.first.value) {
                    progressing.value -= increaseDegree
                    count.value -= 1
                } else {
                    progressing.value += increaseDegree
                    count.value += 1
                }
                rule.first.value = !rule.first.value
            }
        )
        Text(
            text = rule.second
        )
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    AnimationScreen()
}
