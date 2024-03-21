# **모든 쌍 최단 경로 문제 (All Pairs Shortest Paths)**

- 각 쌍의 점 사이의 최단 경로를 찾는 문제

## 다익스트라(Dijkstra)의 최단 경로 알고리즘 이용

- 각 점을 시작점으로 정하여 다익스트라 알고리즘 수행
- 시간복잡도는 n x O(n^2) = O(n^3)
    - 단, n은 점의 수

## 플로이드-워셜 (**Floyd-Warshall)** 알고리즘

- Warshall
    - 그래프에서 모든 쌍의 경로 존재 여부를 찾아내는 동적 계획 알고리즘을 제안
- Floyd
    - 이를 변형하여 모든 쌍 최단 경로를 찾는 알고리즘을 고안
- 모든 쌍 최단 경로를 찾는 동적 계획 알고리즘을 플로이드-워셜 알고리즘 이라 명칭
- 플로이드 알고리즘의 시간복잡도는 O(n^3)으로 다익스트라 알고리즘을 n번 사용할 때의 시간복잡도와 동일
- 플로이드 알고리즘은 매우 간단하여 다익스트라 알고리즘을 사용하는 것보다 효율적

## **플로이드 알고리즘 아이디어**

### 부분 문제들 찾기

- 그래프이 점 3개가 있는 경우, 점 i에서 j까지의 최단 경로는 2가지 경로
  즉, i → 1 → j 또는 i → j 중 짧은 것이 최단 경로

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d62e4c20-f5e9-43ed-b979-2e4db3da89bb/d944a43d-e174-4380-85a6-f0ec2c803d57/Untitled.png)

- 모든 점들을 경유 가능한 점들로 고려하면서 모든 쌍의 최단 경로의 거리를 계산

### 부분 문제 정의

- 단, 입력 그래프의 점을 각각 1 .. n
- Dij = 점 {1, 2, …, k} 만을 가능한 점들로 고려하여 i 와 j까지의 모든 경로 중 가장 짧은 경로
- k가 0인 경우
    - 점 0은 그래프에 없으므로 어떤 점도 경유하지 않는다는 것을 의미하여 주어진 선분의 가중치

## Pseudo code

- AllPairsShortest
- Input: 2차원 배열 D
    - 단, D[i, j] = 선분 (i, j)의 가중치
    - 만일 선분 (i, j)가 존재하지 않으면 D[i, j] = ∞, 모든 i에 대하여 D[i, i] = 0
- Output: 모든 쌍 최단 경로의 거리를 저장한 2차원 배열 D

```kotlin
1. for k = 1 to n
2.     for i = 1 to n (단, i ≠ k)
3.          for j = 1 to n (단, j ≠ k, j ≠ i)
4.                D[i,j] = min { D[i, k] + D[k, j], D[i, j] }
```

## Example

```kotlin
import kotlin.math.min

private class AllPairsShortestExample {
    fun solution(d: Array<IntArray>) {

        for (k in d.indices) {
            for (i in d.indices) {
                if (i == k) {
                    continue
                }
                for (j in d.indices) {
                    if (j == k || j == i) {
                        continue
                    }
//                    println("entry d[$i][$j] = ${d[i][j]}")
                    d[i][j] = min(d[i][j], if (d[i][k] == Int.MAX_VALUE || d[k][j] == Int.MAX_VALUE ) Int.MAX_VALUE else d[i][k] + d[k][j])
//                    println("finish d[$i][$j] = ${d[i][j]}")
                }
            }

            println("k = $k")
            for (i in d.indices) {
                println(d[i].toList())
            }
            println()
        }

    }
}

private fun main() {
    val example = AllPairsShortestExample()

    val d = arrayOf(
        intArrayOf(0, 4, 2, 5, Int.MAX_VALUE),
        intArrayOf(Int.MAX_VALUE, 0, 1, Int.MAX_VALUE, 4),
        intArrayOf(1, 3, 0, 1, 2),
        intArrayOf(-2, Int.MAX_VALUE, Int.MAX_VALUE, 0, 2),
        intArrayOf(Int.MAX_VALUE, -3, 3, 1, 0)
    )

    println(example.solution(d))
}
```

## Time Complexity

- 총 3중 for문 O(n^3) → k, i, j