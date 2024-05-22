package baekjoon.gold.solution_2565

private class Solution2565 {

    fun solution(
        n: Int,
        graph: Array<Pair<Int, Int>>
    ): Int {
        val sortedGraph = graph.sortedBy { it.first }
        val dp = IntArray(n) { 1 }

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (sortedGraph[i].second > sortedGraph[j].second) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }

        return n - (dp.maxOrNull() ?: 0)
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)

        a to b
    }

    bw.append("${Solution2565().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}