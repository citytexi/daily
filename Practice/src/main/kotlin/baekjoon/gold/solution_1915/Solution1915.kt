package baekjoon.gold.solution_1915

private class Solution1915 {

    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>
    ): Int {
        var maxResult = 0

        val dp = Array(n) { IntArray(m) }

        for (i in 0 until n) {
            for (j in 0 until m) {
                dp[i][j] = map[i][j]

                if (i == 0 || j == 0) {
                    // map의 가장자리 부분은 크기가 1 보다 큰 경우가 존재하지 않음
                    maxResult = maxOf(maxResult, dp[i][j])
                    continue
                }

                if (dp[i][j] == 0) {
                    // 현재 칸이 1이 아닌 경우 제외
                    maxResult = maxOf(maxResult, dp[i][j])
                    continue
                }

                val leftTop = dp[i - 1][j - 1]
                val rightTop = dp[i - 1][j]
                val leftBottom = dp[i][j - 1]

                val min = minOf(leftTop, minOf(rightTop, leftBottom))

                dp[i][j] = min + 1
                maxResult = maxOf(maxResult, dp[i][j])
            }
        }

        return maxResult * maxResult
    }

}


private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) {
        val input = br.readLine()
        IntArray(m) { col -> input[col].digitToInt() }
    }

    bw.append("${Solution1915().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}