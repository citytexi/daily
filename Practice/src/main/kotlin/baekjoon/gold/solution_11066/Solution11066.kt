package baekjoon.gold.solution_11066

private class Solution11066 {

    fun solution(
        k: Int,
        c: IntArray
    ): Long {
        val dp: Array<LongArray> = Array(k + 1) { LongArray(k + 1) }
        val sum = LongArray(k + 1)

        for (i in 1 .. k) {
            sum[i] = sum[i - 1] + c[i - 1]
        }

        for (i in 1 .. k) {
            for (j in 1 .. k - i) {
                dp[j][j + i] = Long.MAX_VALUE

                for (t in j until j + i) {
                    dp[j][j + i] = minOf(dp[j][j + i], dp[j][t] + dp[t + 1][j + i] + sum[j + i] - sum[j - 1])
                }
            }
        }

        return dp[1][k]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    repeat(t) {
        val k = br.readLine().toInt()
        val inputs = br.readLine().split(" ").map(String::toInt).toIntArray()
        bw.append("${Solution11066().solution(k, inputs)}\n")
    }

    bw.flush()

    br.close()
    bw.close()
}