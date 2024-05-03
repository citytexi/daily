package baekjoon.silver.solution_2156

import kotlin.math.max

private class Solution2156 {
    fun solution(cups: IntArray): Int = when (cups.size) {
        2 -> cups[1]
        3 -> cups[1] + cups[2]
        else -> {
            val dp = IntArray(cups.size) {
                when (it) {
                    1 -> cups[1]
                    2 -> cups[1] + cups[2]
                    else -> 0
                }
            }

            for (i in 3 until dp.size) {
                dp[i] = max(dp[i - 2] + cups[i], dp[i - 3] + cups[i - 1] + cups[i])
                dp[i] = max(dp[i], dp[i - 1])
            }

            dp.last()
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val cups = IntArray(n + 1) {
        when (it) {
            0 -> 0
            else -> br.readLine().toInt()
        }
    }

    bw.append("${Solution2156().solution(cups)}\n")
    bw.flush()

    br.close()
    bw.close()
}