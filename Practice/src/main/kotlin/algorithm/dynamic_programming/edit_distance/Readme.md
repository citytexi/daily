# 편집 거리 문제 (Edit Distance Problem)

- 문자열 S를 수정하여 문자열 T로 변환하려고 할 때 쓰이는 연산
    - 삽입 - insert
    - 삭제 - delete
    - 대체 - substitude
- 편집 거리 - S를 T로 변환시키는데 필요한 최소 편집 횟수

## 편집 거리 예시

- S - strong
- T - stone
    1. stong - r 삭제
    2. stone - g → e 대체
- 총 2회

## Idea

- 각 문자열의 접두부의 편집거리를 미리 알고있으면 부분 문제를 통해 현재를 알 수 있음
- stro → sto, ng → ne를 알고 있으면 strong → stone을 알 수 있음

## Pseudo code

- EditDistance
- Input
    - S: String, T: String 단, S와 T의 길이는 m 과 n
- Output
    - S를 T로 변환하는 편집거리 E[m, n]

```kotlin
1. for i = 0 to m E[i, 0] = i    // 0번 열의 초기화
2. for j = 0 to n E[0, j] = j     // 0번 행의 초기화
3. for i = 1 to m
4.     for j = 1 to n
5.          E[i, j] = min{ E[i, j - 1] + 1, E[i - 1, j] + 1, E[i - 1, j - 1] + α }
6. return E[m, n]
```

## Example Code

```kotlin
import kotlin.math.min

private class EditDistanceExample {
    fun solution(s: String, t: String): Int {
        val e = Array(s.length + 1) { IntArray(t.length + 1) { 0 } }

        for (i in 1 .. s.length) {
            e[i][0] = i
        }

        for (j in 1 .. t.length) {
            e[0][j] = j
        }

        for (i in e.indices) {
            println(e[i].toList())
        }

        println()

        for (i in 1 .. s.length) {
            for (j in 1 .. t.length) {
                e[i][j] = min(e[i][j - 1] + 1, min(e[i - 1][j] + 1, e[i - 1][j - 1] + if (s[i - 1] == t[j - 1]) 0 else 1 ))
            }
        }

        for (i in e.indices) {
            println(e[i].toList())
        }

        return e[s.length][t.length]
    }
}

private fun main() {
    val example = EditDistanceExample()

    println(example.solution(s = "strong", t = "stone"))
}
```

## Time Complexity

- O(mn)
    - 단 m과 n은 두 문자열의 길이