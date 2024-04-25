package baekjoon.silver.solution_2579

import kotlin.math.max

private class Solution2579 {

    fun solution(arr: IntArray): Int {
        val dp = IntArray(arr.size)

        return when (dp.size) {
            2 -> arr[1]
            3 -> arr[1] + arr[2]
            else -> {
                dp[1] = arr[1]
                dp[2] = arr[1] + arr[2]

                for (i in 3 until dp.size) {
                    dp[i] = max(dp[i - 2] + arr[i], dp[i - 3] + arr[i] + arr[i - 1])
                }

                dp.last()
            }
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = IntArray(n + 1) {
        when (it) {
            0 -> 0
            else -> br.readLine().toInt()
        }
    }

    bw.append("${Solution2579().solution(arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}