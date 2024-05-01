package baekjoon.silver.solution_11727

private class Solution11727 {
    fun solution(n: Int): Int {
        // n = 2 -> 2 * 2, (2 * 1) * 2, (1 * 2) * 2
        val dp = IntArray(n + 1) {
            when (it) {
                1 -> 1
                2 -> 3
                else -> 0
            }
        }

        if (n > 2) {
            for (i in 3 .. n) {
                dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10_007
            }
        }

        return dp[n]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    bw.append("${Solution11727().solution(n)}\n")
    bw.flush()

    br.close()
    bw.close()
}