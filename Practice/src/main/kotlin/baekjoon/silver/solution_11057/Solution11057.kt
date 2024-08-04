package baekjoon.silver.solution_11057

private class Solution11057 {

    fun solution(n: Int): Int {
        val dp = Array(n + 1) { row ->
            IntArray(10) {
                when (row) {
                    1 -> 1
                    else -> 0
                }
            }
        }

        for (k in 2..n) {
            for (i in dp[0].indices) {
                for (j in 0..i) {
                    dp[k][i] = (dp[k][i] + dp[k - 1][j]) % 10_007
                }
            }
        }

        return dp[n].toList().reduce { acc, num -> (acc + num) % 10_007 }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    bw.append("${Solution11057().solution(n)}")
    bw.flush()

    br.close()
    bw.close()
}