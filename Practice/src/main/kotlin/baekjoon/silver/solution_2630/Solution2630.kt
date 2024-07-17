package baekjoon.silver.solution_2630

private class Solution2630 {
    private var blue = 0
    private var white = 0

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): Pair<Int, Int> {
        blue = 0
        white = 0
        dfs(
            size = n,
            map = map
        )

        return white to blue
    }

    private fun dfs(
        size: Int,
        map: Array<IntArray>,
        row: Int = 0,
        col: Int = 0,
    ) {
        if (size == 1 || check(size, map, row, col)) {
            when (map[row][col]) {
                1 -> blue += 1
                else -> white += 1
            }
        } else {
            dfs(size/2, map, row, col)
            dfs(size/2, map, row + size/2, col)
            dfs(size/2, map, row, col + size/2)
            dfs(size/2, map, row + size/2, col + size/2)
        }
    }

    private fun check(
        size: Int,
        map: Array<IntArray>,
        row: Int,
        col: Int
    ): Boolean {
        val current = map[row][col]

        for (nextRow in row until row + size) {
            for (nextCol in col until col + size) {
                if (map[nextRow][nextCol] != current) {
                    return false
                }
            }
        }

        return true
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    val result = Solution2630().solution(n, map)
    bw.append("${result.first}\n")
    bw.append("${result.second}\n")
    bw.flush()

    br.close()
    bw.close()
}