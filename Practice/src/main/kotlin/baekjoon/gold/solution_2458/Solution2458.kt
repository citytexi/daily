package baekjoon.gold.solution_2458

private class Solution2458 {

    fun solution(
        n: Int,
        m: Int,
        graph: Array<IntArray>
    ): Int {
        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (graph[i][k] != 1) {
                        continue
                    }

                    if (graph[k][j] != 1) {
                        continue
                    }

                    graph[i][j] = 1
                }
            }
        }

        var result = 0

        for (i in 1..n) {
            var count = 0

            for (j in 1..n) {
                if (graph[i][j] != 1 && graph[j][i] != 1) {
                    continue
                }

                count += 1
            }

            if (count == n - 1) {
                result += 1
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val graph = Array(n + 1) { IntArray(n + 1) }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a][b] = 1
    }

    bw.append("${Solution2458().solution(n, m, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}