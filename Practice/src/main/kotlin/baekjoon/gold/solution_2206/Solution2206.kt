package baekjoon.gold.solution_2206

private class Solution2206 {
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
        var result = Int.MAX_VALUE

        val deque = ArrayDeque<Triple<Int, Int, Int>>().apply {
            add(Triple(0, 0, 1))
        }
        val visited = Array(n) { Array(m) { IntArray(2) } }
        visited[0][0][1] = 1

        while (deque.isNotEmpty()) {
            val (row, col, chance) = deque.removeFirst()

            if (row == n - 1 && col == m - 1) {
                result = visited[row][col][chance]
                break
            }

            for (direction in directions) {
                val nextRow = row + direction.first
                val nextCol = col + direction.second

                if (nextRow !in 0 until n || nextCol !in 0 until m) {
                    continue
                }

                when (map[nextRow][nextCol]) {
                    0 -> if (visited[nextRow][nextCol][chance] == 0) {
                        deque.add(Triple(nextRow, nextCol, chance))
                        visited[nextRow][nextCol][chance] = visited[row][col][chance] + 1
                    }
                    1 -> if (chance == 1) {
                        deque.add(Triple(nextRow, nextCol, 0))
                        visited[nextRow][nextCol][0] = visited[row][col][1] + 1
                    }
                }
            }
        }


        return when (result) {
            Int.MAX_VALUE -> -1
            else -> result
        }
    }

    data class Node(
        val row: Int,
        val col: Int,
        val distance: Int,
        val isBreakWall: Boolean = false
    )

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) {
        val str = br.readLine().toCharArray()
        IntArray(m) { str[it].digitToInt() }
    }

    bw.append("${Solution2206().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}