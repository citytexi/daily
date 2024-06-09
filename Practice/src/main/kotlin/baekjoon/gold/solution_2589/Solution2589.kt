package baekjoon.gold.solution_2589

private class Solution2589 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    fun solution(
        r: Int,
        c: Int,
        map: Array<CharArray>
    ): Int {
        var result = 0

        for (row in 0 until r) {
            for (col in 0 until c) {
                if (map[row][col] == 'L') {
                    result = maxOf(result, bfs(row, col, r, c,  map))
                }
            }
        }

        return result
    }

    private fun bfs(
        row: Int,
        col: Int,
        r: Int,
        c: Int,
        map: Array<CharArray>
    ): Int {
        val deque = ArrayDeque<Triple<Int, Int, Int>>().apply { add(Triple(row, col, 0)) }
        val visited = Array(r) { IntArray(c) { Int.MAX_VALUE } }
        visited[row][col] = 0

        var maxValue = 0

        while (deque.isNotEmpty()) {
            val (currentRow, currentCol, currentTime) = deque.removeFirst()

            if (maxValue < currentTime) {
                maxValue = currentTime
            }

            for (direction in directions) {
                val nextRow = currentRow + direction.first
                val nextCol = currentCol + direction.second
                val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (nextValue == 'W') {
                    continue
                }

                if (visited[nextRow][nextCol] <= currentTime + 1) {
                    continue
                }

                visited[nextRow][nextCol] = currentTime + 1
                deque.add(Triple(nextRow, nextCol, currentTime + 1))
            }
        }

        return maxValue
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (r, c) = br.readLine().split(" ").map(String::toInt)

    val map = Array(r) { br.readLine().toCharArray() }

    bw.append("${Solution2589().solution(r, c, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}