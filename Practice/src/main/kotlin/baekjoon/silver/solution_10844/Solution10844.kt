package baekjoon.silver.solution_10844

private class Solution10844 {
    fun solution(n: Int): Int {
        val dp = Array(n + 1) { row ->
            when (row) {
                0 -> IntArray(10) { col ->
                    when (col) {
                        0 -> 0
                        else -> 1
                    }
                }
                else -> IntArray(10)
            }
        }

        for (i in 0 until n - 1) {
            dp[i + 1][0] = dp[i][1]
            dp[i + 1][9] = dp[i][8]

            for (j in 1 .. 8) {
                dp[i + 1][j] = (dp[i][j - 1] + dp[i][j + 1]) % 1_000_000_000
            }
        }

        return dp[n - 1].reduce{ acc, num ->
            (acc + num) % 1_000_000_000
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append("${Solution10844().solution(br.readLine().toInt())}\n")
    bw.flush()

    br.close()
    bw.close()
}
