package baekjoon.silver.solution_1926

private class Solution1926 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1,
    )

    private var maxSize = 0
    private var currentSize = 0

    fun solution(
        n: Int,
        m: Int,
        graph: Array<IntArray>
    ): String {
        var count = 2

        for (row in graph.indices) {
            for (col in graph[row].indices) {
                val current = graph[row][col]

                if (current != 1) {
                    continue
                }
                currentSize = 0
                dfs(row, col, count, graph)
                maxSize = maxOf(maxSize, currentSize)
                count += 1
            }
        }

        val sb = StringBuilder()

        sb.append("${count - 2}\n")
        sb.append(maxSize)

        return sb.toString()
    }

    private fun dfs(
        row: Int,
        col: Int,
        count: Int,
        graph: Array<IntArray>
    ) {
        graph[row][col] = count
        currentSize += 1

        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second
            val nextValue = graph.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            if (nextValue != 1) {
                continue
            }

            dfs(nextRow, nextCol, count, graph)
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val graph = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append(Solution1926().solution(n, m, graph))
    bw.flush()

    br.close()
    bw.close()
}