package baekjoon.gold.solution_1956

private class Solution1956 {

    fun solution(
        v: Int,
        e: Int,
        graph: Array<IntArray>
    ): Int {
        for (k in 1 .. v) {
            for (i in 1 .. v) {
                for (j in 1 .. v) {
                    val ik = graph[i][k]
                    val kj = graph[k][j]
                    val value = if (ik == Int.MAX_VALUE || kj == Int.MAX_VALUE) Int.MAX_VALUE else ik + kj
                    graph[i][j] = minOf(graph[i][j], value)
                }
            }
        }

        var answer = Int.MAX_VALUE
        for (i in 1 ..v) {
            answer = minOf(graph[i][i], answer)
        }

        return when (answer) {
            Int.MAX_VALUE -> -1
            else -> answer
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (v, e) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(v + 1) { IntArray(v + 1) { Int.MAX_VALUE } }

    repeat(e) {
        val (a, b, c) = br.readLine().split(" ").map(String::toInt)
        graph[a][b] = c
    }

    bw.append("${Solution1956().solution(v, e, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}