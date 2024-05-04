package baekjoon.silver.solution_11659


private class Solution11659(nums: IntArray) {
    private var dp: IntArray = IntArray(nums.size + 1)

    init {
        for (i in 1 until dp.size) {
            dp[i] = dp[i - 1] + nums[i - 1]
        }
    }

    fun solution(i: Int, j: Int): Int = dp[j] - dp[i - 1]
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val nums = br.readLine().split(" ").map(String::toInt).toIntArray()
    val solution11659 = Solution11659(nums)

    repeat(m) {
        val (i, j) = br.readLine().split(" ").map(String::toInt)
        bw.append("${solution11659.solution(i, j)}\n")
    }

    bw.flush()

    br.close()
    bw.close()
}