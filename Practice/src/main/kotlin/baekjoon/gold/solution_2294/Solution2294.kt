package baekjoon.gold.solution_2294

import kotlin.math.min

private class Solution2294 {
    fun solution(k: Int, coins: IntArray): Int {
        coins.sort()

        val dp = IntArray(k + 1) {
            when (it) {
                in coins -> 1
                else -> Int.MAX_VALUE
            }
        }

        for (i in coins.indices) {
            for (j in 1 .. k) {
                if (j - coins[i] >= 0) {
                    val prev = if (dp[j - coins[i]] == Int.MAX_VALUE) Int.MAX_VALUE else dp[j - coins[i]] + 1
                    dp[j] = min(dp[j], prev)
                }
            }
        }

        return when (dp[k]) {
            Int.MAX_VALUE -> -1
            else -> dp[k]
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)
    val coins = IntArray(n) { br.readLine().toInt() }

    bw.append("${Solution2294().solution(k, coins)}\n")
    bw.flush()

    br.close()
    bw.close()
}