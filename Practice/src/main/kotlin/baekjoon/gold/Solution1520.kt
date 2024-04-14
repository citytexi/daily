package baekjoon.gold

private class Solution1520 {
    private val directions = arrayOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )

    fun solution(
        m: Int,
        n: Int,
        map: Array<IntArray>
    ): Int = dfs(
        currentM = 0,
        currentN = 0,
        m = m,
        n = n,
        map = map,
        dp = Array(m) { IntArray(n) { -1 } }
    )

    private fun dfs(
        currentM: Int,
        currentN: Int,
        m: Int,
        n: Int,
        map: Array<IntArray>,
        dp: Array<IntArray>
    ): Int {
        when {
            currentM == m - 1 && currentN == n - 1 -> return 1
            dp[currentM][currentN] != -1 -> return dp[currentM][currentN]
            else -> dp[currentM][currentN] = 0
        }

        for (direction in directions) {
            val nextM = currentM + direction.first
            val nextN = currentN + direction.second

            if (nextM !in 0 until m || nextN !in 0 until n) {
                continue
            }

            if (map[currentM][currentN] <= map[nextM][nextN]) {
                continue
            }

            dp[currentM][currentN] += dfs(nextM, nextN, m, n, map, dp)
        }

        return dp[currentM][currentN]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (m, n) = br.readLine().split(" ").map(String::toInt)

    val map = Array(m) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution1520().solution(m, n, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}