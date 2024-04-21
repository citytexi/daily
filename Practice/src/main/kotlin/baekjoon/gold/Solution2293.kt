package baekjoon.gold

private class Solution2293 {
    fun solution(k: Int, coins: IntArray): Int {
        val dp = IntArray(k + 1) {
            when (it) {
                0 -> 1
                else -> 0
            }
        }

        val sortedCoins = coins.sortedArray()

        for (i in sortedCoins.indices) {
            for (j in 0 .. k) {
                if (j - coins[i] >= 0) {
                    dp[j] += dp[j - coins[i]]
                }
            }
        }

        return dp[k]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)
    val coins = IntArray(n) { br.readLine().toInt() }

    bw.append("${Solution2293().solution(k, coins)}\n")
    bw.flush()

    br.close()
    bw.close()
}