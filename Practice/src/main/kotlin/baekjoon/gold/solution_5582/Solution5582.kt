package baekjoon.gold.solution_5582

private class Solution5582 {

    fun solution(
        first: String,
        second: String,
    ): Int {
        val dp = Array(first.length + 1) { IntArray(second.length + 1) }

        var max = 0

        for (row in 1 .. first.length) {
            for (col in 1 .. second.length) {
                if (first[row - 1] == second[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1] + 1
                }

                max = maxOf(max, dp[row][col])
            }
        }

        return max
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val first = br.readLine()
    val second = br.readLine()

    bw.append(Solution5582().solution(first, second).toString())
    bw.flush()

    br.close()
    bw.close()
}