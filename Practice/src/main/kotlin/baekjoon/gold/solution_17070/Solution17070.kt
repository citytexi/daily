package baekjoon.gold.solution_17070

private class Solution17070 {
    private var result = 0

    fun solution(
        n: Int,
        map: Array<IntArray>
    ) {
        result = 0
        dfs(0, 1, Direction.HORIZONTAL, map)
        println(result)
    }

    private fun dfs(
        row: Int,
        col: Int,
        direction: Direction,
        map: Array<IntArray>
    ) {
        if (row !in map.indices || col !in map.indices) {
            return
        }

        if (map[row][col] == 1) {
            return
        }

        if (direction == Direction.DIAGONAL) {
            if (map[row - 1][col] == 1 || map[row][col - 1] == 1) {
                return
            }
        }

        if (row == map.lastIndex && col == map.lastIndex) {
            result += 1
            return
        }

        when (direction) {
            Direction.HORIZONTAL -> {
                dfs(row, col + 1, Direction.HORIZONTAL, map)
                dfs(row + 1, col + 1, Direction.DIAGONAL, map)
            }
            Direction.VERTICAL -> {
                dfs(row + 1, col, Direction.VERTICAL, map)
                dfs(row + 1, col + 1, Direction.DIAGONAL, map)
            }
            Direction.DIAGONAL -> {
                dfs(row, col + 1, Direction.HORIZONTAL, map)
                dfs(row + 1, col, Direction.VERTICAL, map)
                dfs(row + 1, col + 1, Direction.DIAGONAL, map)
            }
        }
    }

    private enum class Direction {
        VERTICAL,
        HORIZONTAL,
        DIAGONAL
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    Solution17070().solution(n, map)


    br.close()
}