package baekjoon.silver.solution_1965

private class Solution1965 {

    fun solution(
        n: Int,
        graph: List<Int>,
    ) : Int {
        val dp = IntArray(graph.size) { 1 }

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (graph[i] > graph[j]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }

        return dp.max()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt() // 1 <= n <= 1000
    val graph = br.readLine().split(" ").map(String::toInt)

    bw.append(Solution1965().solution(n, graph).toString())
    bw.flush()

    br.close()
    bw.close()
}
