package algorithm.dynamic_programming.coin_change

private class CoinChangeExample {
    fun solution(
        n: Int,
        d: IntArray
    ) {
        println(dPCoinChange(n, d))
    }

    private fun dPCoinChange(
        n: Int,
        d: IntArray
    ): Int {
        val dp = IntArray(n + 1) {
            when (it) {
                0 -> 0
                else -> Int.MAX_VALUE
            }
        }

        for (j in 1 .. n) {
            for (i in d.indices) {
                if (d[i] <= j && dp[j - d[i]] + 1 < dp[j]) {
                    dp[j] = dp[j - d[i]] + 1
                }
            }
        }

        return dp[n]
    }
}

private fun main() {
    val n = 20
    val d = intArrayOf(
        16,
        10,
        5,
        1
    )

    val example = CoinChangeExample()
    example.solution(n, d)
}