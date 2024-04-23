package baekjoon.silver.solution_2667

private class Solution2667 {
    private val directions = arrayOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )

    private var count = 0

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): List<Int> {
        val visited = Array(n) { BooleanArray(n) }
        val result = mutableListOf<Int>()

        for (row in map.indices) {
            for (col in map[row].indices) {
                if (!visited[row][col] && map[row][col] == 1) {
                    count = 0
                    dfs(row, col, map, visited)
                    result.add(count)
                }
            }
        }


        return result.sorted()
    }

    private fun dfs(
        row: Int,
        col: Int,
        map: Array<IntArray>,
        visited: Array<BooleanArray>
    ) {
        if (map[row][col] == 0) {
            return
        }

        visited[row][col] = true
        count += 1

        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second
            val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            if (visited[nextRow][nextCol]) {
                continue
            }

            if (nextValue == 1) {
                dfs(nextRow, nextCol, map, visited)
            }
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val map = Array(n) { _ ->
        val str = br.readLine()
        IntArray(n) { col -> str[col].digitToInt() }
    }

    val result = Solution2667().solution(n, map)

    bw.append("${result.size}\n")
    for (i in result.indices) {
        bw.append("${result[i]}\n")
    }

    bw.flush()

    br.close()
    bw.close()
}