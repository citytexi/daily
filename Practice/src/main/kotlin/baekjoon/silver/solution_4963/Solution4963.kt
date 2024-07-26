package baekjoon.silver.solution_4963

private class Solution4963 {
    private val directions = arrayOf(
        -1 to -1,
        -1 to 0,
        -1 to 1,
        0 to -1,
        0 to 1,
        1 to -1,
        1 to 0,
        1 to 1
    )

    fun solution(
        w: Int,
        h: Int,
        graph: Array<IntArray>
    ): Int {
        var count = 2

        for (row in graph.indices) {
            for (col in graph[row].indices) {
                if (graph[row][col] != 1) {
                    continue
                }
                graph[row][col] = count++
                dfs(row, col, graph)
            }
        }

        return count - 2
    }

    private fun dfs(
        row: Int,
        col: Int,
        graph: Array<IntArray>
    ) {
        val currentValue = graph[row][col]

        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second
            val nextValue = graph.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            when (nextValue) {
                1 -> Unit
                else -> continue
            }

            graph[nextRow][nextCol] = currentValue
            dfs(nextRow, nextCol, graph)
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    while (true) {
        val (w, h) = br.readLine().split(" ").map(String::toInt)

        if (w == 0 && h == 0) {
            break
        }

        val graph = Array(h) { br.readLine().split(" ").map(String::toInt).toIntArray() }

        bw.append("${Solution4963().solution(w, h, graph)}\n")
    }

    bw.flush()


    br.close()
    bw.close()
}