package baekjoon.silver

import kotlin.math.cos
import kotlin.math.min

/*
문제
RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

1번 집의 색은 2번 집의 색과 같지 않아야 한다.
N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
입력
첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
* */

private class Solution1149 {
    fun solution(n: Int, costs: List<ColorCost>): Int {
        val dp = Array(n + 1) { 0 to 0 }
        // red 1, green 2, blue 3
        var result = 0
        dp[1] = if (costs[0].redCost < costs[0].greenCost) {
            // red < green, blue 위치 모름
            if (costs[0].blueCost < costs[0].redCost) {

                costs[0].blueCost to 3
            } else {
                costs[0].redCost to 1
            }
        } else {
            // red > green, blue 위치 모름
            if (costs[0].blueCost < costs[0].greenCost) {
                costs[0].blueCost to 3
            } else {
                costs[0].greenCost to 2
            }
        }
        result += dp[1].first

        for (i in 2 until dp.size) {
            dp[i] = when (dp[i - 1].second) {
                1 -> {
                    // 이전 red 선택, blue green 중 선택
                    if (costs[i - 1].blueCost < costs[i - 1].greenCost) {
                        costs[i - 1].blueCost to 3
                    } else {
                        costs[i - 1].greenCost to 2
                    }
                }
                2 -> {
                    // 이전 green 선택, red blue 중 선택
                    if (costs[i - 1].redCost < costs[i - 1].blueCost) {
                        costs[i - 1].redCost to 1
                    } else {
                        costs[i - 1].blueCost to 3
                    }
                }
                else -> {
                    // 이전 blue 선택, red green 중 선택
                    if (costs[i - 1].redCost < costs[i - 1].greenCost) {
                        costs[i - 1].redCost to 1
                    } else {
                        costs[i - 1].greenCost to 2
                    }
                }
            }
            result += dp[i].first
        }


        println(dp.toList())
        println(result)
        return 0
    }

    data class ColorCost(
        val redCost: Int,
        val greenCost: Int,
        val blueCost: Int,
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1149 = Solution1149()
    val n = br.readLine().toInt()
    val costs = mutableListOf<Solution1149.ColorCost>()
    repeat(n) {
        val (redCost, greenCost, blueCost) = br.readLine().split(" ").map { it.toInt() }
        costs.add(Solution1149.ColorCost(redCost, greenCost, blueCost))
    }

    bw.append("${solution1149.solution(n, costs)}\n")
    bw.flush()

    br.close()
    bw.close()
}