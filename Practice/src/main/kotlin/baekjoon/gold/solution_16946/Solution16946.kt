package baekjoon.gold.solution_16946

private class Solution16946 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1,
    )

    private var current = 0

    fun solution(
        n: Int,
        m: Int,
        graph: Array<IntArray>
    ): String {
        var mark = 1

        val visited = Array(n) { IntArray(m) }
        val locations = mutableListOf(-1)

        for (row in 0 until n) {
            for (col in 0 until m) {
                if (graph[row][col] != 0) {
                    continue
                }

                if (visited[row][col] != 0) {
                    continue
                }

                current = 0
                dfs(
                    row = row,
                    col = col,
                    mark = mark,
                    graph = graph,
                    visited = visited,
                )
                locations.add(current)
                mark += 1
            }
        }

        val sb = StringBuilder()

        for (row in 0 until n) {
            for (col in 0 until m) {
                var answer = 1

                when (graph[row][col]) {
                    0 -> sb.append(0)

                    else -> {
                        val checked = BooleanArray(locations.size)

                        for (direction in directions) {
                            val nextRow = row + direction.first
                            val nextCol = col + direction.second
                            val nextValue = graph.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                            if (nextValue != 0) {
                                continue
                            }

                            if (checked[visited[nextRow][nextCol]]) {
                                continue
                            }

                            checked[visited[nextRow][nextCol]] = true
                            answer += locations[visited[nextRow][nextCol]]
                        }

                        sb.append(answer % 10)
                    }
                }
            }
            sb.append("\n")
        }

        return sb.toString()
    }

    private fun dfs(
        row: Int,
        col: Int,
        mark: Int,
        graph: Array<IntArray>,
        visited: Array<IntArray>,
    ) {
        visited[row][col] = mark
        current += 1

        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second
            val nextValue = graph.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            if (nextValue != 0) {
                continue
            }

            if (visited[nextRow][nextCol] != 0) {
                continue
            }

            dfs(
                row = nextRow,
                col = nextCol,
                mark = mark,
                graph = graph,
                visited = visited,
            )
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val graph = Array(n) {
        val str = br.readLine()
        IntArray(m) { col -> str[col].digitToInt() }
    }

    bw.append(Solution16946().solution(n, m, graph))
    bw.flush()

    br.close()
    bw.close()
}