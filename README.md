# [kotlin Function](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)

<details>
<summary>debounce</summary>
<div markdown="1">

![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/82709044/186551211-ca4b6d50-00f2-4667-a41f-6a0e679bd1e3.gif)

  로직
```
  1. debounce 이후 체크박스 상태 값이 담긴 리스트를 보낸다.
  2. Repository 내에 이전에 캐시해 둔 체크박스 상태 리스트와 값이 들어온 리스트 값을 비교한다.
  3. 두개의 값이 다르면 해당 규칙의 상태 값을 서버에 보낸다.
  4. 캐시의 값을 바꾼다.
```
  
이렇게 로직을 만들면 유저가 체크박스를 계속 눌렀을 때, 
  
유저가 의도적으로 체크박스를 계속 눌러도 debounce 때문에 
  
반복적인 터치 이후에 한번만 데이터 전송이 가게끔 만들 수 있다.

</div>
</details>

