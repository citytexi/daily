package baekjoon.gold

private class Solution14503 {
    private val directions = arrayOf(
        -1 to 0, // 상
        0 to -1, // 좌
        1 to 0, // 하
        0 to 1 // 우
    )

    fun solution(
        n: Int,
        m: Int,
        r: Int,
        c: Int,
        d: Int,
        map: Array<IntArray>
    ): Int {
        var currentRow = r
        var currentCol = c

        var currentDirectionIndex = when (d) {
            0 -> 0
            1 -> 3
            2 -> 2
            else -> 1
        }

        var result = 0
        var run = true

        while (run) {

            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (map[currentRow][currentCol] == 0) {
                result += 1
                map[currentRow][currentCol] = 2
            }

            when (checkAllDirectionValue(currentRow, currentCol, map)) {
                true -> {
                    // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                    currentDirectionIndex = (currentDirectionIndex + 1) % 4
                    val currentDirection = directions[currentDirectionIndex]
                    val nextRow = currentRow + currentDirection.first
                    val nextCol = currentCol + currentDirection.second

                    when (map.getOrNull(nextRow)?.getOrNull(nextCol)) {
                        0 -> {
                            currentRow = nextRow
                            currentCol = nextCol
                        }
                    }
                }
                false -> {
                    // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                    val backDirection = directions[(currentDirectionIndex + 2) % 4]
                    val nextRow = currentRow + backDirection.first
                    val nextCol = currentCol + backDirection.second

                    when (map.getOrNull(nextRow)?.getOrNull(nextCol)) {
                        null, 1 -> run = false
                        else -> {
                            currentRow = nextRow
                            currentCol = nextCol
                        }
                    }
                }
            }
        }

        return result
    }

    private fun checkAllDirectionValue(
        row: Int,
        col: Int,
        map: Array<IntArray>
    ): Boolean {
        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second

            val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            if (nextValue == 0) {
                return true
            }
        }

        return false
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val (r, c, d) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution14503().solution(n, m, r, c, d, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}