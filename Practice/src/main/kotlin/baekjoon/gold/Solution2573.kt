package baekjoon.gold

private class Solution2573 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>
    ): Int {
        var current = map
        var iceCount = bfs(current)
        var result = 0

        while (iceCount == 1) {
            current = current.melting()
            iceCount = bfs(current)
            result += 1
        }

        return when (iceCount) {
            0 -> 0
            else -> result
        }
    }

    private fun Array<IntArray>.melting(): Array<IntArray> {
        val copy = Array(this.size) { row -> IntArray(this[row].size) { col -> this[row][col] } }

        for (row in this.indices) {
            for (col in this[row].indices) {
                for (direction in directions) {
                    val nextRow = row + direction.first
                    val nextCol = col + direction.second

                    this.getOrNull(nextRow)?.getOrNull(nextCol)?.let { nextValue ->
                        if (copy[row][col] != 0 && nextValue == 0) {
                            copy[row][col] -= 1
                        }
                    }
                }
            }
        }

        return copy
    }

    private fun bfs(map: Array<IntArray>): Int {
        val visited = Array(map.size) { BooleanArray(map[it].size) }
        var iceCount = 0

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    val deque = ArrayDeque<Pair<Int, Int>>().apply { add(i to j) }
                    iceCount++

                    while (deque.isNotEmpty()) {
                        val (row, col) = deque.removeFirst()
                        visited[row][col] = true

                        for (direction in directions) {
                            val nextRow = row + direction.first
                            val nextCol = col + direction.second

                            map.getOrNull(nextRow)?.getOrNull(nextCol)?.let { nextValue ->
                                if (nextValue != 0 && !visited[nextRow][nextCol]) {
                                    deque.add(nextRow to nextCol)
                                    visited[nextRow][nextCol] = true
                                }
                            }
                        }
                    }
                }

            }
        }

        return iceCount
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) { _ -> br.readLine().split(" ").let { str -> IntArray(m) { str[it].toInt() } } }

    bw.append("${Solution2573().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}