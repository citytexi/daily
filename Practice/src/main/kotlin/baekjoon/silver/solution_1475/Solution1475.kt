package baekjoon.silver.solution_1475

private class Solution1475 {

    fun solution(n: String): Int {
        val dp = IntArray(10)

        for (i in n) {
            when (val index = i.digitToInt()) {
                9 -> dp[6] += 1
                else -> dp[index] += 1
            }
        }
        dp[6] = if (dp[6] % 2 > 0) {
            (dp[6] / 2) + 1
        } else {
            dp[6] / 2
        }

        return dp.max()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine()

    bw.append("${Solution1475().solution(n)}\n")
    bw.flush()

    br.close()
    bw.close()
}