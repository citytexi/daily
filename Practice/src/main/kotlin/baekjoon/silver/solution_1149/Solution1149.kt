package baekjoon.silver.solution_1149

import kotlin.math.min

private class Solution1149 {
    fun solution(map: Array<Triple<Int, Int, Int>>): Int {
        val dp = Array(3) { IntArray(map.size) }
        dp[0][0] = map[0].first
        dp[1][0] = map[0].second
        dp[2][0] = map[0].third

        for (i in 1 until map.size) {
            dp[0][i] = map[i].first + minOf(dp[1][i - 1], dp[2][i - 1])
            dp[1][i] = map[i].second + minOf(dp[0][i - 1], dp[2][i - 1])
            dp[2][i] = map[i].third + minOf(dp[0][i - 1], dp[1][i - 1])
        }

        return min(dp[0][map.lastIndex], min(dp[1][map.lastIndex], dp[2][map.lastIndex]))
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val map = Array(n) {
        val (r, g, b) = br.readLine().split(" ").map(String::toInt)

        Triple(r, g, b)
    }

    bw.append("${Solution1149().solution(map)}\n")
    bw.flush()

    br.close()
    bw.close()
}