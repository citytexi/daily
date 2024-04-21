package baekjoon.gold

import kotlin.math.min

private class Solution11404 {
    fun solution(
        n: Int,
        graph: Array<IntArray>
    ) {
        for (k in graph.indices) {
            for (i in graph.indices) {
                if (i == k) {
                    continue
                }
                for (j in graph[i].indices) {
                    if (i == j || k == j) {
                        continue
                    }
                    val ik = graph[i][k]
                    val kj = graph[k][j]
                    graph[i][j] = min(graph[i][j], if (ik == Int.MAX_VALUE || kj == Int.MAX_VALUE) Int.MAX_VALUE else ik + kj )
                }
            }
        }

        val bw = System.out.bufferedWriter()
        for (row in graph.indices) {
            for (col in graph[row].indices) {
                val value = when (graph[row][col]) {
                    Int.MAX_VALUE -> 0
                    else -> graph[row][col]
                }
                bw.append(
                    when (col) {
                        graph[row].lastIndex -> "$value"
                        else -> "$value "
                    }
                )
            }
            bw.append("\n")
        }
        bw.flush()
        bw.close()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val graph = Array(n) { row ->
        IntArray(n) { col ->
            when (col) {
                row -> 0
                else -> Int.MAX_VALUE
            }
        }
    }

    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map(String::toInt)
        graph[a - 1][b - 1] = min(graph[a - 1][b - 1], c)
    }

    Solution11404().solution(n, graph)

    br.close()
}