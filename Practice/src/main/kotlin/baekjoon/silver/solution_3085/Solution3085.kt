package baekjoon.silver.solution_3085

private class Solution3085 {
    private val directions = arrayOf(
        1 to 0,
        0 to 1,
    )

    fun solution(graph: Array<CharArray>): Int {
        var max = 0

        for (row in graph.indices) {
            for (col in graph[row].indices) {
                max = maxOf(max, search(row, col, graph))

                for (direction in directions) {
                    val nextRow = row + direction.first
                    val nextCol = col + direction.second
                    val nextValue = graph.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                    if (graph[row][col] == nextValue) {
                        continue
                    }

                    val temp = graph[row][col]
                    graph[row][col] = nextValue
                    graph[nextRow][nextCol] = temp

                    max = maxOf(max, maxOf(search(row, nextCol, graph), search(nextRow, col, graph)))

                    graph[nextRow][nextCol] = graph[row][col]
                    graph[row][col] = temp
                }
            }
        }

        return max
    }

    private fun search(
        row: Int,
        col: Int,
        graph: Array<CharArray>
    ): Int {
        var countRow = 1
        var countCol = 1

        var nextRow = row - 1

        while (nextRow >= 0 && graph[nextRow][col] == graph[row][col]) {
            countRow += 1
            nextRow -= 1
        }

        nextRow = row + 1
        while (nextRow < graph.size && graph[nextRow][col] == graph[row][col]) {
            countRow += 1
            nextRow += 1
        }

        var nextCol = col - 1
        while (nextCol >= 0 && graph[row][nextCol] == graph[row][col]) {
            countCol += 1
            nextCol -= 1
        }

        nextCol = col + 1
        while (nextCol < graph.size && graph[row][nextCol] == graph[row][col]) {
            countCol++
            nextCol++
        }

        return maxOf(countRow, countCol)
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val graph = Array(n) { br.readLine().toCharArray() }

    bw.append(Solution3085().solution(graph).toString())
    bw.flush()

    br.close()
    bw.close()
}