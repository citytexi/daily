package baekjoon.gold.solution_13023

private class Solution13023 {
    private var result = false

    fun solution(
        n: Int,
        graph: Array<MutableList<Int>>
    ): String {
        for (i in 0 until n) { // 모든 노드에 대해 DFS 수행
            val visited = BooleanArray(n)
            visited[i] = true
            dfs(0, i, graph, visited)
        }

        return when (result) {
            true -> "1\n"
            false -> "0\n"
        }
    }

    private fun dfs(
        depth: Int,
        index: Int,
        graph: Array<MutableList<Int>>,
        visited: BooleanArray
    ) {
        if (depth == 4 || result) {
            result = true
            return
        }

        for (sub in graph[index]) {
            if (visited[sub]) {
                continue
            }

            visited[sub] = true
            dfs(depth + 1, sub, graph, visited)
            visited[sub] = false
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n) { mutableListOf<Int>() }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a].add(b)
        graph[b].add(a)
    }

    bw.append(Solution13023().solution(n, graph))
    bw.flush()

    br.close()
    bw.close()
}