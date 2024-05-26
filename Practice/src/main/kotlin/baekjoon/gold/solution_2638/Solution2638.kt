package baekjoon.gold.solution_2638

private class Solution2638 {
    private val directions = arrayOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )


    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>
    ): Int {
        var time = 0

        val visited = Array(n) { IntArray(m) }

        while (true) {
            var currentCheese = 0

            for (row in map.indices) {
                for (col in map[row].indices) {
                    if (map[row][col] == 1) {
                        currentCheese += 1
                    }
                }
            }

            if (currentCheese == 0) {
                break
            }

            dfs(0, 0, n, m, map, visited)

            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (visited[row][col] >= 2) {
                        map[row][col] = 0
                    }
                    visited[row][col] = 0
                }
            }

            time += 1
        }

        return time
    }

    private fun dfs(
        row: Int,
        col: Int,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        visited: Array<IntArray>
    ) {
        if (map[row][col] == 1) {
            visited[row][col] += 1
            return
        }

        visited[row][col] = 2

        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second
            val nextVisited = visited.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

            if (nextVisited >= 2) {
                continue
            }

            dfs(nextRow, nextCol, n, m, map, visited)
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution2638().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}