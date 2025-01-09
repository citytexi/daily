package baekjoon.gold.solution_2533

private class Solution2533 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Int>>
    ): Int {
        val dp = Array(n + 1) { IntArray(2) }
        val visited = BooleanArray(n + 1)

        dfs(dp, graph, visited, 1)

        return minOf(dp[1][0], dp[1][1])
    }

    private fun dfs(
        dp: Array<IntArray>,
        graph: Array<MutableList<Int>>,
        visited: BooleanArray,
        depth: Int,
    ) {
        visited[depth] = true

        dp[depth][0] = 0
        dp[depth][1] = 1

        for (sub in graph[depth]) {
            if (visited[sub]) {
                continue
            }

            dfs(dp, graph, visited, sub)

            dp[depth][0] += dp[sub][1]
            dp[depth][1] += minOf(dp[sub][0], dp[sub][1])
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(n - 1) {
        val (u, v) = br.readLine().split(" ").map(String::toInt)

        graph[u].add(v)
        graph[v].add(u)
    }

    bw.append(Solution2533().solution(n, graph).toString())
    bw.flush()

    br.close()
    bw.close()
}