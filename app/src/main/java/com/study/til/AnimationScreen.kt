package com.study.til

import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen(
    viewModel: MainViewModel
) {
    val rules by viewModel.rules.collectAsState()
    val progress by viewModel.progressRating.collectAsState()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        item {
            ProgressBarWithAnimation(progress)
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
fun ProgressBarWithAnimation(
    progress: Float
) {
    val progressRating by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
    )
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.absoluteOffset(x = maxWidth.times(progressRating).minus(24.dp)),
                painter = painterResource(id = R.drawable.ic_run),
                contentDescription = ""
            )
        }
        RoundedProgressIndicator(
            progress = progressRating,
            trackColor = Color.Blue,
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
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
