package baekjoon.silver.solution_2583

import java.lang.StringBuilder

private class Solution2583 {
    private val directions = arrayOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0,
    )
    private var currentCount = 0

    fun solution(
        m: Int,
        n: Int,
        graph: Array<IntArray>,
    ): String {
        val sb = StringBuilder()

        var count = 1
        val countList = mutableListOf<Int>()
        for (row in graph.indices) {
            for (col in graph[row].indices) {
                if (graph[row][col] != 0) {
                    continue
                }

                graph[row][col] = count
                currentCount = 0
                dfs(
                    row = row,
                    col = col,
                    depth = 0,
                    count = count,
                    graph = graph
                )

                count += 1
                countList.add(currentCount)
            }
        }

        sb.append("${countList.size}\n")
        sb.append("${countList.sorted().joinToString(" ")}\n")

        return sb.toString()
    }

    private fun dfs(
        row: Int,
        col: Int,
        depth: Int,
        count: Int,
        graph: Array<IntArray>,
    ) {
        currentCount += 1


        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second
            val nextValue = graph.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            if (nextValue != 0) {
                continue
            }

            graph[nextRow][nextCol] = count
            dfs(
                row = nextRow,
                col = nextCol,
                depth = depth + 1,
                count = count,
                graph = graph,
            )
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (m, n, k) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(m) { IntArray(n) }

    repeat(k) {
        val (leftBottomX, leftBottomY, rightTopX, rightTopY) = br.readLine().split(" ").map(String::toInt)

        for (row in leftBottomY until rightTopY) {
            for (col in leftBottomX until rightTopX) {
                graph[row][col] = -1
            }
        }
    }

    bw.append(Solution2583().solution(m, n, graph))
    bw.flush()

    br.close()
    bw.close()
}
