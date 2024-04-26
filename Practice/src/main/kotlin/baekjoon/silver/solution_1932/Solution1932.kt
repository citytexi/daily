package baekjoon.silver.solution_1932

import kotlin.math.max

private class Solution1932 {
    fun solution(n: Int, graph: Array<IntArray>): Int {
        val dp = Array(n) { row -> IntArray(graph[row].size) }
        dp[0][0] = graph[0][0]

        for (i in 1 until dp.size) {
            for (j in dp[i].indices) {
                val prev = dp.getOrNull(i - 1)?.getOrNull(j - 1) ?: 0
                val next = dp.getOrNull(i - 1)?.getOrNull(j) ?: 0
                dp[i][j] = max(prev, next) + graph[i][j]
            }
        }

        return dp[n - 1].maxOrNull()!!
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n) { row ->
        when (row) {
            0 -> {
                val str = br.readLine().toInt()
                IntArray(1) { str }
            }
            else -> {
                val str = br.readLine().split(" ")
                IntArray(row + 1) { col -> str[col].toInt() }
            }
        }
    }

    bw.append("${Solution1932().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}