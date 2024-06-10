package baekjoon.gold.solution_10942

private class Solution10942(
    private val nums: IntArray,
    private val dp: Array<BooleanArray> = Array(nums.size + 1) { row ->
        BooleanArray(nums.size + 1){ col ->
            row == col
        }
    }
) {
    init {
        for (i in 1 until nums.size) {
            for (j in 0 until nums.size - i) {
                dp[j][j + i] = nums[j] == nums[j + i] && (dp[j + 1][j + i - 1] || j + 1 > j + i - 1)
            }
        }
    }

    fun solution(
        s: Int,
        e: Int
    ): Int = when (dp[s][e]) {
        true -> 1
        false -> 0
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val nums = br.readLine().split(" ").map(String::toInt).toIntArray()
    val m = br.readLine().toInt()

    val solution10942 = Solution10942(nums)

    repeat(m) {
        val (s, e) = br.readLine().split(" ").map(String::toInt)
        bw.append("${solution10942.solution(s - 1, e - 1)}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}