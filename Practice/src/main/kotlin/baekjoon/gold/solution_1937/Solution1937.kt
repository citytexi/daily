package baekjoon.gold.solution_1937

private class Solution1937 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    private var result = 0

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): Int {
        result = 0

        val visited = Array(n) { IntArray(n) }

        for (row in 0 until n) {
            for (col in 0 until n) {
                result = maxOf(result, dfs(row, col, map, visited))
            }
        }

        return result
    }

    private fun dfs(
        row: Int,
        col: Int,
        map: Array<IntArray>,
        visited: Array<IntArray>
    ): Int = when (visited[row][col]) {
        0 -> {
            visited[row][col] = 1

            for (direction in directions) {
                val nextRow = row + direction.first
                val nextCol = col + direction.second
                val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (map[row][col] >= nextValue) {
                    continue
                }

                visited[row][col] = maxOf(visited[row][col], dfs(nextRow, nextCol, map, visited) + 1)
            }

            visited[row][col]
        }
        else -> visited[row][col]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution1937().solution(n, map)}\n")
    bw.flush()


    br.close()
    bw.close()
}