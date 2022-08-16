package com.study.til

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen(
    viewModel: MainViewModel
) {
    val rules by viewModel.rules.collectAsState()
    LazyColumn(Modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        item {
            ProgressBarWithAnimation()
            Spacer(modifier = Modifier.size(20.dp))
        }
        items(rules) { rule ->
            CheckBoxItem(
                rule = rule,
                setIsChecked = viewModel::setIsChecked
            )
        }
    }
}

@Composable
fun ProgressBarWithAnimation() {
//    val divideAni by animateFloatAsState(
//        targetValue = progressing.value,
//        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
//    )
    Column(Modifier.fillMaxWidth()) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
//            val imgAni by animateDpAsState(
//                targetValue = (maxWidth / size) * count.value - 24.dp,
//                animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
//            )
            Image(
//                modifier = Modifier.absoluteOffset(x = imgAni),
                painter = painterResource(id = R.drawable.ic_run),
                contentDescription = ""
            )
        }
        RoundedProgressIndicator(
            progress = 0.0f,
            trackColor = Color.Blue,
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun CheckBoxItem(
    rule: Rule,
    setIsChecked: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = rule.isChecked,
            onCheckedChange = {
                setIsChecked(rule.id)
            }
        )
        Text(
            text = rule.ruleText
        )
    }
}
