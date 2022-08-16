## Linear Progress Bar 커스텀

## 결과
https://user-images.githubusercontent.com/82709044/184907223-ce2a3f47-47d5-4421-a450-fed709bd1d8e.mp4

### MainViewModel
``` kotlin
val progressRating: StateFlow<Float> = rules.map { rules ->
    var rating = 0.0f
    rules.forEach { rule -> if (rule.isChecked) rating += (1.0 / _rules.value.size).toFloat() }
    rating
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000L), 0.0f)
```
- progress 진행 상태를 Float로 저장한다.
- 진행 상태는 rules를 for 문 돌려서 `isChecked` 가 `true` 일 때 `1.0/(총 리스트 수)`를 Float로 변환한 것을 더한다.

### ProgressBarWithAnimation
``` kotlin
val progressRating by animateFloatAsState(
    targetValue = progress,
    animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
)
```
- `viewModel` 내에 있는 `progressRating` 이 바뀔 때, `600ms`에 거쳐서 Float 값을 수정한다.

``` kotlin
BoxWithConstraints(Modifier.fillMaxWidth()) {
    Image(
        modifier = Modifier.absoluteOffset(x = maxWidth.times(progressRating).minus(24.dp)),
        painter = painterResource(id = R.drawable.ic_run),
        contentDescription = ""
    )
}
```
- `BoxWithConstraints` 내에 있는 content는 `maxWidth`를 통해서 핸드폰의 총 너비를 사용할 수 있다.
- 하지만 `maxWidth`는 Dp 값이므로 Float와 곱셈 뺄셈과 같은 연산들은 `times`, `minus` 등으로 연산한다.
- 이를 통해서 `(핸드폰의 총 너비)*(현재 진행률) - 24.dp` 를 통해서 image의 x 좌표 dp를 구한다.
- `-24.dp'를 한 이유 : 이미지 asset이 48x48 dp 이며, 프로그래스 바 끝 부분 중앙에 이미지를 두고 싶기 때문이다.

### RoundedProgressIndicator
``` kotlin
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
```
- `Canvas`를 사용해서 size.width 를 통해 핸드폰의 전체 너비를 얻는다. (size.width는 Float로 준다.!!)
- 첫 drawLine은 background, 두번째 drawLine은 진행률을 표시한다.
- 현재 진행률은 `(현재 진행률) * (핸드폰 전체 너비)` 를 통해서 구한다.
