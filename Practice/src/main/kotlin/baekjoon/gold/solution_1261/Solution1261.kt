package baekjoon.gold.solution_1261

private class Solution1261 {
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

        val deque = ArrayDeque<Triple<Int, Int, Int>>().apply {
            add(Triple(0, 0, 0))
        }
        val visited = Array(m) { IntArray(n) { Int.MAX_VALUE } }

        while (deque.isNotEmpty()) {
            val (currentRow, currentCol, currentCount) = deque.removeFirst()

            for (direction in directions) {
                val nextRow = currentRow + direction.first
                val nextCol = currentCol + direction.second
                val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                val nextCount = when (nextValue) {
                    1 -> currentCount + 1
                    else -> currentCount
                }

                if (visited[nextRow][nextCol] <= nextCount) {
                    continue
                }

                visited[nextRow][nextCol] = nextCount

                deque.add(Triple(nextRow, nextCol, nextCount))
            }
        }

        return when (val value = visited[m - 1][n - 1]) {
            Int.MAX_VALUE -> 0
            else -> value
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val map = Array(m) {
        val inputs = br.readLine()
        IntArray(n) { col -> inputs[col].digitToInt() }
    }

    bw.append("${Solution1261().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}