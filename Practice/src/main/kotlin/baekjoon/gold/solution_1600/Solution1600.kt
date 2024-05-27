package baekjoon.gold.solution_1600

private class Solution1600 {
    private val horseDirections = arrayOf(
        -2 to -1,
        -1 to -2,
        -2 to 1,
        -1 to 2,
        2 to -1,
        1 to -2,
        2 to 1,
        1 to 2
    )

    private val directions = arrayOf(
        -1 to 0,
        1 to 0,
        0 to 1,
        0 to -1
    )

    fun solution(
        k: Int,
        w: Int,
        h: Int,
        map: Array<IntArray>
    ): Int {
        val visited = Array(h) { Array(w) { IntArray(k + 1) { -1 } } }

        bfs(k, w, h, map, visited)

        var result = w * h
        var isSuccessLogic = false

        for (i in 0 .. k) {
            if (visited[h - 1][w - 1][i] != -1) {
                isSuccessLogic = true
                result = minOf(result, visited[h - 1][w - 1][i])
            }
        }

        return when (isSuccessLogic) {
            true -> result
            false -> -1
        }
    }

    private fun bfs(
        k: Int,
        w: Int,
        h: Int,
        map: Array<IntArray>,
        visited: Array<Array<IntArray>>
    ) {
        val deque = ArrayDeque<Triple<Int, Int, Int>>().apply { add(Triple(0, 0, 0)) }

        repeat(k) {
            visited[0][0][it] = 0
        }

        while (deque.isNotEmpty()) {
            val (row, col, jumpCount) = deque.removeFirst()

            if (row == h && col == w) {
                return
            }

            for (direction in directions) {
                val nextRow = row + direction.first
                val nextCol = col + direction.second
                val value = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (value == 1) {
                    continue
                }

                if (visited[nextRow][nextCol][jumpCount] != -1) {
                    continue
                }

                visited[nextRow][nextCol][jumpCount] = visited[row][col][jumpCount] + 1
                deque.add(Triple(nextRow, nextCol, jumpCount))
            }

            if (jumpCount < k) {
                for (direction in horseDirections) {
                    val nextRow = row + direction.first
                    val nextCol = col + direction.second
                    val value = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                    if (value == 1) {
                        continue
                    }

                    if (visited[nextRow][nextCol][jumpCount + 1] != -1) {
                        continue
                    }

                    visited[nextRow][nextCol][jumpCount + 1] = visited[row][col][jumpCount] + 1
                    deque.add(Triple(nextRow, nextCol, jumpCount + 1))
                }
            }
        }

    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val k = br.readLine().toInt()
    val (w, h) = br.readLine().split(" ").map(String::toInt)

    val map = Array(h) { row ->
        val inputs = br.readLine().split(" ")
        IntArray(w) { col -> inputs[col].toInt() }
    }

    bw.append("${Solution1600().solution(k, w, h, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}