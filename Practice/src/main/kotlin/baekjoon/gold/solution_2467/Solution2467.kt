package baekjoon.gold.solution_2467

import kotlin.math.abs

private class Solution2467 {

    fun solution(
        n: Int,
        nums: LongArray
    ): String {
        var start = 0
        var end = nums.lastIndex

        var min = Long.MAX_VALUE
        var left = 0L
        var right = 0L

        while (start < end) {
            val sum = abs(nums[start] + nums[end])

            if (sum < min) {
                left = nums[start]
                right = nums[end]
                min = sum
            }

            when {
                nums[start] + nums[end] > 0 -> end -= 1
                nums[start] + nums[end] < 0 -> start += 1
                else -> break
            }
        }

        return "$left $right\n"
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val nums = br.readLine().split(" ").map(String::toLong).toLongArray()

    bw.append(Solution2467().solution(n, nums))
    bw.flush()

    br.close()
    bw.close()
}