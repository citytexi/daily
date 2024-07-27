package baekjoon.silver.solution_11048

private class Solution11048 {
    private val directions = arrayOf(
        1 to 0,
        0 to 1,
        1 to 1
    )

    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>
    ): Int {
        val dp = Array(n) { IntArray(m) }.apply { this[0][0] = map[0][0] }

        for (row in 0 until n) {
            for (col in 0 until m) {
                var temp = 0

                for (direction in directions) {
                    val preRow = row - direction.first
                    val preCol = col - direction.second
                    val preValue = dp.getOrNull(preRow)?.getOrNull(preCol) ?: continue

                    temp = maxOf(temp, preValue)
                }

                dp[row][col] = map[row][col] + temp
            }
        }


        return dp[n - 1][m - 1]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution11048().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}