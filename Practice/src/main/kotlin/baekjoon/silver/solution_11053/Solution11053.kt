package baekjoon.silver.solution_11053

import kotlin.math.max

private class Solution11053 {
    fun solution(n: Int, a: IntArray): Int {
        val dp = IntArray(a.size) { 1 }

        for (i in a.indices) {
            for (j in 0 until i) {
                if (a[i] > a[j]) {
                    dp[i] = max(dp[i], dp[j] + 1)
                }
            }
        }

        return dp.maxOrNull()!!
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val a = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution11053().solution(n, a)}\n")
    bw.flush()

    br.close()
    bw.close()
}

