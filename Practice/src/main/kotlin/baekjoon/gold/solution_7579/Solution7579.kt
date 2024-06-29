package baekjoon.gold.solution_7579

private class Solution7579 {

    fun solution(
        n: Int,
        m: Int,
        actCost: IntArray,
        disableCost: IntArray
    ): Int {
        val dp = Array(n + 1) { LongArray(100001) }

        for (i in 1..n) {
            for (j in dp[0].indices) {
                if (disableCost[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]
                } else {
                    dp[i][j] = maxOf(dp[i-1][j], dp[i - 1][j - disableCost[i - 1]] + actCost[i - 1])
                }
            }
        }

        var answer = 0

        for (i in dp[n].indices) {
            if (dp[n][i] >= m) {
                answer = i
                break
            }
        }

        return answer
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val actCost = br.readLine().split(" ").map(String::toInt).toIntArray()
    val disableCost = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution7579().solution(n, m, actCost, disableCost)}\n")
    bw.flush()

    br.close()
    bw.close()
}