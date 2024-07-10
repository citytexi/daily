package baekjoon.silver.solution_2961

import kotlin.math.abs

private class Solution2961 {
    private var min = Int.MAX_VALUE

    fun solution(
        n: Int,
        graph: Array<IntArray>
    ): Int {
        min = Int.MAX_VALUE
        val visited = BooleanArray(n)

        for (i in 0 until n) {
            visited[i] = true
            dfs(graph[i][0], graph[i][1], n, graph, visited)
            visited[i] = false
        }

        return min
    }

    private fun dfs(
        a: Int,
        b: Int,
        n: Int,
        graph: Array<IntArray>,
        visited: BooleanArray
    ) {
        min = minOf(min, abs(a - b))

        for (i in 0 until n) {
            if (visited[i]) {
                continue
            }
            visited[i] = true
            dfs(a * graph[i][0], b + graph[i][1], n, graph, visited)
            visited[i] = false
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution2961().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}