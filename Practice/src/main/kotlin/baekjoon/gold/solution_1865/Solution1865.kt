package baekjoon.gold.solution_1865

private class Solution1865 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Pair<Int, Int>>>
    ): String = when (bfs(n, graph)) {
        true -> "YES\n"
        false -> "NO\n"
    }

    private fun bfs(
        n: Int,
        graph: Array<MutableList<Pair<Int, Int>>>
    ): Boolean {
        val result = IntArray(n + 1) { 10000000 }.apply { this[1] = 0 }

        var update = false

        for (i in 1 until n) {
            for (j in 1..n) {
                for ((next, nextDistance) in graph[j]) {
                    val sumDistance = nextDistance + result[j]

                    if (result[next] > sumDistance) {
                        result[next] = sumDistance
                        update = true
                    }
                }
            }

            if (!update) {
                break
            }
        }

        if (update) {
            for (i in 1..n) {
                for ((next, nextDistance) in graph[i]) {
                    val sumDistance = nextDistance + result[i]

                    if (result[next] > sumDistance) {
                        return true
                    }
                }
            }
        }

        return false
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val testCase = br.readLine().toInt()

    repeat(testCase) {
        val (n, m, w) = br.readLine().split(" ").map(String::toInt)

        val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

        repeat(m) {
            val (s, e, t) = br.readLine().split(" ").map(String::toInt)
            graph[s].add(e to t)
            graph[e].add(s to t)
        }
        repeat(w) {
            val (s, e, t) = br.readLine().split(" ").map(String::toInt)
            graph[s].add(e to -t)
        }

        bw.append(Solution1865().solution(n, graph))
    }
    bw.flush()

    br.close()
    bw.close()
}