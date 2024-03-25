# 배낭 문제 (Knapsack Problem)

- 물건 n개
- 각 물건 i, 무게 wi, 가치 vi
- 배낭 용량 C
- 위 조건에서 배낭에 담을 수 있는 물건의 최대 가치를 찾는 문제
- 단, 배낭에 담을 물건의 무게 합이 C를 초과하지 않고 각 물건은 1개씩만 존재
- 이러한 문제를 0-1 배낭이라고 명칭

## 부분 문제

- K[i, w]
- 물건의 1 ~ i 까지만 고려하고, 배낭의 용량이 w일 때의 최대 가치
- 최적해 - K[n, C]
- C의 값이 매우 크면 알고리즘 수행시간이 매우 길어짐

## Pseudo code

- Knapsack
- Input
    - 배낭의 용량 C
    - n개의 물건
    - 각 물건 i의 무게 w 와 가치 v
- Output - K[n, C]

```kotlin
1. for i = 0 to n   K[i, 0] = 0    // 배낭의 용량이 0일 때
2. for w = 0 to C  K[0, w] = 0  // 물건 0이란 어떤 물건도 고려하지 않을 때
3.
4. for i = 1 to n {
5.    for w = 1 to C {          // w는 배낭의 (임시) 용량
6.        if ( wi > w )	      // 물건 i의 무게가 임시 배낭 용량을 초과하면
7.           K[i, w] = K[i - 1, w]
8.        else                 // 물건 i를 배낭에 담지 않을 경우와 담을 경우 고려
9.           K[i, w] = max{ K[i - 1, w], K[i - 1, w - wi] + vi }
10.     }
11.    }
12. return K[n, C]
```

## Example code

```kotlin
import kotlin.math.max

private class KnapsackExample {
    private lateinit var dp: Array<IntArray>
    fun solution(c: Int, list: List<KnapsackObject>) {
        dp = Array(list.size + 1) { IntArray(c + 1) }

        knapsack(c, list)

        for (i in dp.indices) {
            println(dp[i].toList())
        }

        println()
    }

    private fun knapsack(c: Int, list: List<KnapsackObject>) {
        for (i in 1 .. list.size) {
            val item = list[i - 1]
            for (w in 1 .. c) {
                if (item.w > w) {
                    // 물건 i의 무게가 임시 배낭 용량을 초과하면
                    dp[i][w] = dp[i - 1][w]
                } else {
//                    println("dp[i][w] = ${dp[i][w]}")
//                    println("dp[i][w - item.w] + item.v = ${dp[i - 1][w - item.w]} + ${item.v} = ${dp[i - 1][w - item.w] + item.v}")
//                    println()
                    dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - item.w] + item.v)
                }
            }

            for (t in dp.indices) {
                println(dp[t].toList())
            }

            println()
        }
    }

    data class KnapsackObject(
        val i: Int,
        val w: Int,
        val v: Int
    )
}

private fun main() {
    val example = KnapsackExample()

    val c = 10
    val list = listOf(
        KnapsackExample.KnapsackObject(
            i = 1,
            w = 5,
            v = 10,
        ),
        KnapsackExample.KnapsackObject(
            i = 2,
            w = 4,
            v = 40,
        ),
        KnapsackExample.KnapsackObject(
            i = 3,
            w = 6,
            v = 30,
        ),
        KnapsackExample.KnapsackObject(
            i = 4,
            w = 3,
            v = 50,
        )
    )

    example.solution(c, list)
}
```

## Time Complexity

- 부분 문제의 수 → n개 x 용량 C
- 따라서, O(nC)

## 주의사항

- 일반적인 Knapsack 문제
    - 물건의 개수가 1개가 아님 - Unbounded knapsack problem
    - 물건의 무게가 정수가 아님
    - 배낭의 용량이 정수가 아님
- 일반적인 Knapsack 문제는 NP complete