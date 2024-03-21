# 연속 행렬 곱셈 (Chained Matrix Multiplications) 문제

- 연속된 행렬들의 곱셈에 필요한 원소간의 최소 곱셈 횟수를 찾는 문제

## 문제 예시

- 행렬 A가 10x20, 행렬 B가 20x5, 행렬 C가 5x15

  ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d62e4c20-f5e9-43ed-b979-2e4db3da89bb/d1e8c376-5b03-4475-a97c-68b23a7e6e23/Untitled.png)

- (A x B) x C의 경우
    - A x B 계산 → 10 x 20 x 5 = 1,000번
    - AB x C 계산 → 10 x 5 x 15 = 750번
    - 총 1,750번
- A x (B x C)의 경우
    - B x C 계산 → 20 x 5 x 15 = 1,500번
    - A x BC 계산 → 10 x 20 x 15 = 3,000번
    - 총 4,500번

## Idea

- 동일한 결과값 이지만 곱셈 연산 횟수는 2,800번의 차이가 생김
- 곱셈 횟수를 최소화 시키기 위한 곱셈 순서를 찾아야함
- 주어진 행렬의 순서를 지켜서 이웃하는 행렬끼리 곱해야하기 때문

### 부분 문제

- 크기 1
    - A, B, C, D, E
- 크기 2
    - A x B, B x C, C x D, D x E
- 크기 3
    - A x B x C, B x C x D, C x D x E
- 크기 4
    - A x B x C x D, B x C x D x E
- 크기 5
    - A x B x C x D x E

## Pseudo code

- MatrixChain
- Input: 연속된 행렬 A1 x A2 x … x An
    - 단, A1 = d0 x d1, A2 = d1 x d2, …, An = dn-1 x dn
- Output: 입력된 행렬 곱셈에 필요한 원소의 최소 곱셈 횟수

```kotlin
1. for i = 1 to n 
2.         C[i,i] = 0 
3. for L = 1 to n-1 { // L은 부분 문제의 크기를 조절하는 인덱스
4.       for i = 1 to n-L {
5.              j = i + L
6.             C[i, j] = ∞
7.             for k = i to j - 1 {
8.                     temp = C[i, k] + C[k + 1, j] + d(i-1)dkdj
9.                     if (temp < C[i, j])
10.                             C[i, j] = temp 
11.                          }
12.                   }
13.      }
14. return C[1, n]
```

## Example

```kotlin
private class ChainedMatrixMultiplicationsExample {
    private lateinit var dp: Array<IntArray>
    fun solution(chainedMatrix: Array<Matrix>): Int {
        dp = Array(chainedMatrix.size) { row ->
            IntArray(chainedMatrix.size) { col ->
                when (col) {
                    row -> 0
                    else -> Int.MAX_VALUE
                }
            }
        }

        for (l in 1 until chainedMatrix.size) {
//            println("L = $l")
            for (i in 0 until  chainedMatrix.size - l) {
                val j = i + l
//                println("i = $i,  j = $j\n")

                for (k in i until j) {
//                    println("k = $k")

                    val prevD = chainedMatrix[i].rows
                    val currentD = chainedMatrix[k].cols
                    val nextD = chainedMatrix[j].cols
//                    println("prevD = $prevD, currentD = $currentD, nextD = $nextD")

                    val temp = dp[i][k] + dp[k + 1][j] + (prevD * currentD * nextD)
//                    println("${dp[i][k]} + ${dp[k + 1][j]} + ${(prevD * currentD * nextD)}\n")

                    if (temp < dp[i][j]) {
//                        println("dp[i][j] = ${dp[i][j]}, temp = $temp \n")
                        dp[i][j] = temp
                    }
                }
            }
//            println()

//            for (i in dp.indices) {
//                println(dp[i].toList())
//            }
//
//            println()
        }

//        for (i in dp.indices) {
//            println(dp[i].toList())
//        }

        return dp[0][chainedMatrix.size - 1]
    }

    data class Matrix(
        val rows: Int,
        val cols: Int
    )
}

private fun main() {
    val example = ChainedMatrixMultiplicationsExample()
    val chainedMatrixArray = arrayOf(
        ChainedMatrixMultiplicationsExample.Matrix(10, 20),
        ChainedMatrixMultiplicationsExample.Matrix(20, 5),
        ChainedMatrixMultiplicationsExample.Matrix(5, 15),
        ChainedMatrixMultiplicationsExample.Matrix(15, 30),
    )

    println(example.solution(chainedMatrixArray))
}
```

## Time Complexity

- 원소 수 n x n = n^2
- k루프가 최대 (n - 1)번 실행
- 따라서 O(n^2) x O(n) = O(n^3)