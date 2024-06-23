package baekjoon.gold.solution_2749

private class Solution2749 {

    fun solution(n: Long): Long {
        val p = 15 * 1_000_000 / 10
        val dp = LongArray(p + 1) { Long.MAX_VALUE }
        dp[0] = 0
        dp[1] = 1

        for (i in 2 .. p) {
            if (dp[i] != Long.MAX_VALUE) {
                continue
            }
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000
        }

        return dp[(n % p).toInt()]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toLong()

    bw.append("${Solution2749().solution(n)}\n")
    bw.flush()

    br.close()
    bw.close()
}