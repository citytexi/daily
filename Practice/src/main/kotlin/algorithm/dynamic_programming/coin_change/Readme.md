# 동전 거스름돈 문제 (Coin Change Problem)

- 정해진 동전 d1, d2, …, dk, 거스름돈 n원
    - 단, d1 > d2 > … > dk = 1

## 부분 문제

- w원을 거슬러 줄 때 i번째 동전을 1개 추가할 때 동전 개수
    - w-di 원을 거슬러 줄 때 필요한 최소 동전의 개수 + 1개
- 1원씩 증가시켜서 문제 해결
- C[j] = min1≤i≤k { C[j - di] + 1 }, if j ≥ di

## Pseudo code

- DPCoinChange
- Input
    - 거스름돈 n원
    - k개의 동전 액면
        - d1 > d2 > … > k = 1
- Output: C[n]

```kotlin
1. for i = 1 to n C[i] = ∞
2. C[0] = 0
3. for j = 1 to n { // j는 1원부터 증가하는 (임시) 거스름돈 액수이고, j=n이면 입력에 주어진 거스름돈이 됨
4.       for i = 1 to k {
5.             if (di ≤ j) and (C[j - di] + 1 < C[j])
6.                      C[j] = C[j - di] + 1
7.            }
8.    }
9. return C[n]
```

## Example Code

```kotlin
private class CoinChangeExample {
    fun solution(
        n: Int,
        d: IntArray
    ) {
        println(dPCoinChange(n, d))
    }

    private fun dPCoinChange(
        n: Int,
        d: IntArray
    ): Int {
        val dp = IntArray(n + 1) {
            when (it) {
                0 -> 0
                else -> Int.MAX_VALUE
            }
        }

        for (j in 1 .. n) {
            for (i in d.indices) {
                if (d[i] <= j && dp[j - d[i]] + 1 < dp[j]) {
                    dp[j] = dp[j - d[i]] + 1
                }
            }
        }

        return dp[n]
    }
}

private fun main() {
    val n = 20
    val d = intArrayOf(
        16,
        10,
        5,
        1
    )

    val example = CoinChangeExample()
    example.solution(n, d)
}
```

## Time Complexity

- O(nk)
    - n은 거스름돈, k는 주어진 동전 액면가 개수