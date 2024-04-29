package baekjoon.silver.solution_1912

import kotlin.math.max

private class Solution1912 {
    fun solution(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        dp[0] = nums[0]

        var max = dp[0]

        for (i in 1 until nums.size) {
            dp[i] = max(nums[i], dp[i - 1] + nums[i])
            max = max(max, dp[i])
        }

        return max
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val nums = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution1912().solution(nums)}\n")
    bw.flush()

    br.close()
    bw.close()
}