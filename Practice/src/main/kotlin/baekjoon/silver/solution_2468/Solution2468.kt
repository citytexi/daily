package baekjoon.silver.solution_2468

private class Solution2468 {
    private val directions = arrayOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): Int {
        var result = 0

        for (i in 0..100) {
            var count = 0
            val visited = Array(n) { BooleanArray(n) }

            repeat(n) { row ->
                repeat(n) { col ->
                    if (visited[row][col] || map[row][col] <= i) {
                        return@repeat
                    }

                    count += 1

                    val deque = ArrayDeque<Pair<Int, Int>>().apply { add(row to col) }
                    visited[row][col] = true

                    while (deque.isNotEmpty()) {
                        val current = deque.removeFirst()

                        for (direction in directions) {
                            val nextRow = current.first + direction.first
                            val nextCol = current.second + direction.second
                            val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                            if (visited[nextRow][nextCol]) {
                                continue
                            }

                            if (nextValue <= i) {
                                continue
                            }

                            deque.add(nextRow to nextCol)
                            visited[nextRow][nextCol] = true
                        }
                    }
                }
            }

            result = maxOf(result, count)
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution2468().solution(n, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}