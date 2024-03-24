package baekjoon.silver

private class Solution1260 {
    fun solution(
        n: Int,
        m: Int,
        v: Int,
        edges: Array<IntArray>
    ) {
        val graph = Array(n + 1) { BooleanArray(n + 1) }

        edges.forEach {
            val (start, end) = it
            graph[start][end] = true
            graph[end][start] = true
        }

        dfs(v, graph, BooleanArray(n + 1) { it == v })
        println()
        bfs(v, graph, BooleanArray(n + 1) { it == v })
    }

    private fun dfs(
        node: Int,
        graph: Array<BooleanArray>,
        visited: BooleanArray
    ) {
        print("$node ")

        for (i in 1 until graph[node].size) {
            if (graph[node][i] && !visited[i]) {
                visited[i] = true
                dfs(i, graph, visited)
            }
        }
    }

    private fun bfs(
        node: Int,
        graph: Array<BooleanArray>,
        visited: BooleanArray
    ) {
        val deque = ArrayDeque<Int>()
        deque.add(node)
        print("$node ")

        while (deque.isNotEmpty()) {
            val first = deque.removeFirst()
            for (i in 1 until graph.size) {
                if (graph[first][i] && !visited[i]) {
                    visited[i] = true
                    deque.add(i)
                    print("$i ")
                }
            }
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m, v) = br.readLine().split(" ").map { it.toInt() }
    val edges = Array(m) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }

    val solution1260 = Solution1260()
    solution1260.solution(n, m, v, edges)

    br.close()
    bw.close()
}