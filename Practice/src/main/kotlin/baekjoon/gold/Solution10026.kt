package baekjoon.gold

private class Solution10026 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    fun solution(
        n: Int,
        firstMap: Array<CharArray>,
        secondMap: Array<CharArray>
    ): Pair<Int, Int> {
        val visitedFirst = Array(firstMap.size) { BooleanArray(firstMap.size) }
        val visitedSecond = Array(secondMap.size) { BooleanArray(secondMap.size) }

        var firstCount = 0
        var secondCount = 0

        for (row in 0 until n) {
            for (col in 0 until n) {
                if (dfs(row, col, firstMap, visitedFirst)) {
                    firstCount += 1
                }
                if (dfs(row, col, secondMap, visitedSecond)) {
                    secondCount += 1
                }
            }
        }

        return secondCount to firstCount
    }

    private fun dfs(
        row: Int,
        col: Int,
        map: Array<CharArray>,
        visited: Array<BooleanArray>
    ): Boolean {
        if (visited[row][col]) {
            return false
        }

        visited[row][col] = true

        for (direction in directions) {
            val nextRow = row + direction.first
            val nextCol = col + direction.second

            if ((map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue) != map[row][col]) {
                continue
            }

            dfs(nextRow, nextCol, map, visited)
        }

        return true
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val firstMap = Array(n) { CharArray(n) }
    val secondMap = Array(n) { CharArray(n) }

    repeat(n) { row ->
        br.readLine().forEachIndexed { col, c ->
            when (c) {
                'R', 'B' -> {
                    firstMap[row][col] = c
                    secondMap[row][col] = c
                }
                'G' -> {
                    firstMap[row][col] = 'R'
                    secondMap[row][col] = c
                }
            }
        }
    }

    val result = Solution10026().solution(n, firstMap, secondMap)

    bw.append("${result.first} ${result.second}\n")
    bw.flush()

    br.close()
    bw.close()
}