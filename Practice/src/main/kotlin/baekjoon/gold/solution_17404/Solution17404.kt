package baekjoon.gold.solution_17404

private class Solution17404 {

    fun solution(
        n: Int,
        graph: Array<IntArray>
    ): Int {
        var result = Int.MAX_VALUE
        val dp = Array(n) { IntArray(3) }

        for (t in 0 until 3) {
            when (t) {
                0 -> {
                    dp[0][0] = graph[0][0]
                    dp[0][1] = Int.MAX_VALUE
                    dp[0][2] = Int.MAX_VALUE
                }

                1 -> {
                    dp[0][0] = Int.MAX_VALUE
                    dp[0][1] = graph[0][1]
                    dp[0][2] = Int.MAX_VALUE
                }

                2 -> {
                    dp[0][0] = Int.MAX_VALUE
                    dp[0][1] = Int.MAX_VALUE
                    dp[0][2] = graph[0][2]
                }
            }

            for (i in 1 until n) {
                dp[i][0] = if (dp[i - 1][1] == Int.MAX_VALUE && dp[i - 1][2] == Int.MAX_VALUE) {
                    Int.MAX_VALUE
                } else {
                    minOf(dp[i - 1][1], dp[i - 1][2]) + graph[i][0]
                }

                dp[i][1] = if (dp[i - 1][0] == Int.MAX_VALUE && dp[i - 1][2] == Int.MAX_VALUE) {
                    Int.MAX_VALUE
                } else {
                    minOf(dp[i - 1][0], dp[i - 1][2]) + graph[i][1]
                }

                dp[i][2] = if (dp[i - 1][0] == Int.MAX_VALUE && dp[i - 1][1] == Int.MAX_VALUE) {
                    Int.MAX_VALUE
                } else {
                    minOf(dp[i - 1][0], dp[i - 1][1]) + graph[i][2]
                }
            }

            for (i in 0 until 3) {
                if (t == i) {
                    continue
                }
                result = minOf(result, dp[n - 1][i])
            }
        }


        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution17404().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}