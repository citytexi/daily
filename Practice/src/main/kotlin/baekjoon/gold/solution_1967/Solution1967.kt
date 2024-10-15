package baekjoon.gold.solution_1967

private class Solution1967 {
    private var answer = 0
    private var end = 0

    fun solution(
        n: Int,
        graph: Array<MutableList<Solution1967.Node>>,
    ): String {
        dfs(1, 0, graph, BooleanArray(n + 1))
        dfs(end, 0, graph, BooleanArray(n + 1))

        val sb = StringBuilder()
        sb.append(answer)
        return sb.toString()
    }

    private fun dfs(
        start : Int,
        distance : Int,
        graph : Array<MutableList<Node>>,
        visited : BooleanArray,
    ) {
        visited[start] =true
        if (answer < distance) {
            answer = distance
            end = start
        }

        for (i in graph[start].indices) {
            val next =  graph[start][i].node
            val nextDistance = distance + graph[start][i].distance
            if (visited[next]) {
                continue
            }
            dfs(next, nextDistance, graph, visited)
        }
    }

    data class Node(
        val node : Int,
        val distance : Int,
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n + 1) { mutableListOf<Solution1967.Node>() }

    repeat(n - 1) {
        val (a, b, c) = br.readLine().split(" ").map(String::toInt)

        graph[a].add(Solution1967.Node(b,c))
        graph[b].add(Solution1967.Node(a,c))
    }

    bw.append(Solution1967().solution(n, graph))
    bw.flush()

    br.close()
    bw.close()
}
