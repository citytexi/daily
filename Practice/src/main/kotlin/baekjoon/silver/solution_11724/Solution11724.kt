package baekjoon.silver.solution_11724


private class Solution11724 {

    fun solution(graph: Array<Node>): Int {
        var result = 0
        val visited = BooleanArray(graph.size)

        for (i in 1 until graph.size) {
            if (dfs(i, graph, visited)) {
                result += 1
            }
        }

        return result
    }

    private fun dfs(
        start: Int,
        graph: Array<Node>,
        visited: BooleanArray
    ): Boolean {
        if (visited[start]) {
            return false
        }

        val node = graph[start]
        visited[start] = true

        for (subNode in node.sub) {
            if (!visited[subNode]) {
                dfs(subNode, graph, visited)
            }
        }

        return true
    }

    data class Node(
        val num: Int,
        val sub: MutableList<Int> = mutableListOf()
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { Solution11724.Node(it) }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a].sub.add(b)
        graph[b].sub.add(a)
    }

    bw.append("${Solution11724().solution(graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}
