package baekjoon.gold

import kotlin.math.max

private class Solution9251 {
    fun solution(a: String, b: String): Int {
        val dp = Array(a.length + 1) { IntArray(b.length + 1) }

        for (row in 1 until dp.size) {
            for (col in 1 until dp[row].size) {
                when (a[row - 1]) {
                    b[col - 1] -> dp[row][col] = dp[row - 1][col - 1] + 1
                    else -> dp[row][col] = max(dp[row - 1][col], dp[row][col - 1])
                }
            }
        }

        return dp[a.length][b.length]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val a = br.readLine()
    val b = br.readLine()

    bw.append("${Solution9251().solution(a, b)}\n")
    bw.flush()

    br.close()
    bw.close()
}