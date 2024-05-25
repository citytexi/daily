package baekjoon.gold.solution_2146

private class Solution2146 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): Int {
        var result = Int.MAX_VALUE

        var number = 2

        for (row in 0 until n) {
            for (col in 0 until n) {
                if (map[row][col] == 1) {
                    map[row][col] = number
                    numberingLand(row, col, map, number)
                    number += 1
                }
            }
        }

        for (row in 0 until n) {
            for (col in 0 until n) {
                if (map[row][col] != 0) {
                    result = minOf(result, bfs(row, col, map, result))
                }
            }
        }

        return result
    }

    private fun numberingLand(
        row: Int,
        col: Int,
        map: Array<IntArray>,
        number: Int
    ) {
        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second

            if (nextRow !in map.indices || nextCol !in map.indices) {
                continue
            }

            if (map[nextRow][nextCol] == 1) {
                map[nextRow][nextCol] = number
                numberingLand(nextRow, nextCol, map, number)
            }
        }
    }

    private fun bfs(
        row: Int,
        col: Int,
        map: Array<IntArray>,
        result: Int
    ): Int {
        val startNumber = map[row][col]

        val deque = ArrayDeque<Triple<Int, Int, Int>>().apply { add(Triple(row, col, 0)) }
        val visited = Array(map.size) { BooleanArray(map.size) }
        visited[row][col] = true

        while (deque.isNotEmpty()) {
            val (currentRow, currentCol, currentDistance) = deque.removeFirst()
            val currentNumber = map[currentRow][currentCol]

            when {
                currentDistance - 1 >= result -> return Int.MAX_VALUE
                currentNumber != 0 && currentNumber != startNumber -> return currentDistance - 1
                else -> {
                    for (direction in directions) {
                        val nextRow = currentRow + direction.first
                        val nextCol = currentCol + direction.second

                        if (nextRow !in map.indices || nextCol !in map.indices) {
                            continue
                        }

                        if (visited[nextRow][nextCol] || map[nextRow][nextCol] == startNumber) {
                            continue
                        }

                        visited[nextRow][nextCol] = true
                        deque.add(Triple(nextRow, nextCol, currentDistance + 1))
                    }
                }
            }
        }

        return Int.MAX_VALUE
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()


    val n = br.readLine().toInt()
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }


    bw.append("${Solution2146().solution(n, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}