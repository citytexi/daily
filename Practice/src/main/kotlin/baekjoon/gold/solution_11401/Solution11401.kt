package baekjoon.gold.solution_11401

private class Solution11401 {

    fun solution(
        n: Int,
        k: Int
    ): Long {
        val dp = LongArray(4_000_001).apply { this[0] = 1 }

        for (i in 1 .. n) {
            dp[i] = (i * dp[i - 1]) % MOD
        }

        val numerator = dp[n]
        val denominator = (dp[n - k] * dp[k]) % MOD

        return numerator * pow(denominator, MOD - 2) % MOD
    }

    private fun pow(
        num: Long,
        expo: Long
    ): Long = when (expo) {
        1L -> num % MOD
        else -> {
            val temp = pow(num, expo / 2)
            when (expo % 2) {
                1L -> temp * temp % MOD * num % MOD
                else -> temp * temp % MOD
            }
        }
    }

    companion object {
        private const val MOD = 1_000_000_007L
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)

    bw.append("${Solution11401().solution(n, k)}")
    bw.flush()

    br.close()
    bw.close()
}