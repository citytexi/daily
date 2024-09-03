package baekjoon.silver.solution_2644

private class Solution2644 {
    private var result = -1

    fun solution(
        n: Int,
        m: Int,
        s: Int,
        e: Int,
        graph: Array<MutableList<Int>>
    ): Int {
        result = -1
        val visited = BooleanArray(101)

        dfs(
            depth = 0,
            s = s,
            e = e,
            graph = graph,
            visited = visited
        )

        return result
    }

    private fun dfs(
        depth: Int,
        s: Int,
        e: Int,
        graph: Array<MutableList<Int>>,
        visited: BooleanArray
    ) {
        if (result != -1) {
            return
        }

        if (s==e) {
            result = depth
            return
        }

        for (next in graph[s]) {
            if (visited[next]) {
                continue
            }

            visited[next] = true
            dfs(
                depth = depth + 1,
                s = next,
                e = e,
                graph = graph,
                visited = visited
            )

            if (result != -1) {
                return
            }
        }
    }


}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val (s, e) = br.readLine().split(" ").map(String::toInt)
    val m = br.readLine().toInt()
    val graph = Array(n + 1){ mutableListOf<Int>() }
    for(i in 0 until m){
        val (start, end) = br.readLine().split(" ").map(String::toInt)
        graph[start].add(end)
        graph[end].add(start)
    }

    bw.append("${Solution2644().solution(n, m, s, e, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}