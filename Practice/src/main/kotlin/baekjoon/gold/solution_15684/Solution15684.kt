package baekjoon.gold.solution_15684

private class Solution15684 {
    private var answer = Int.MAX_VALUE

    fun solution(
        n: Int,
        m: Int,
        h: Int,
        graph: Array<IntArray>
    ): Int {
        answer = Int.MAX_VALUE

        for (i in 1 until 4) {
            dfs(
                line = 1,
                depth = 0,
                max = i,
                n = n,
                h = h,
                graph = graph
            )
        }

        return when (answer) {
            Int.MAX_VALUE -> -1
            else -> answer
        }
    }

    private fun dfs(
        line: Int,
        depth: Int,
        max: Int,
        n: Int,
        h: Int,
        graph: Array<IntArray>
    ) {
        if (depth > max || depth >= answer) {
            return
        }

        if (check(n, h, graph)) {
            answer = minOf(answer, depth)
            return
        }

        for (i in line .. h) {
            for (j in 1 until n) {
                if (graph[i][j] != 0 || graph[i][j + 1] != 0) {
                    continue
                }

                graph[i][j] = 1
                graph[i][j + 1] = 2

                dfs(i, depth + 1, max, n, h, graph)

                graph[i][j] = 0
                graph[i][j + 1] = 0
            }
        }
    }

    private fun check(
        n: Int,
        h: Int,
        graph: Array<IntArray>
    ): Boolean {
        for (i in 1..n) {
            var x = 1
            var y = i

            for (j in 0 until h) {
                when (graph[x][y]) {
                    1 -> y += 1
                    2 -> y -= 1
                }

                x += 1
            }

            if (y != i) {
                return false
            }
        }

        return true
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.`out`.bufferedWriter()

    val (n, m, h) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(h + 1) { IntArray(n + 1) }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a][b] = 1
        graph[a][b + 1] = 2
    }

    bw.append("${Solution15684().solution(n, m, h, graph)}")
    bw.flush()


    br.close()
    bw.close()
}