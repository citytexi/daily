package baekjoon.silver

private class Solution11726 {
    fun solution(n: Int): Int {
        val dp = IntArray(n + 1) {
            when (it) {
                1 -> 1
                2 -> 2
                else -> 0
            }
        }

        for (i in 3 .. n) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10_007
        }

        return dp[n]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution11726 = Solution11726()
    bw.append("${solution11726.solution(br.readLine().toInt())}\n")
    bw.flush()

    br.close()
    bw.close()
}