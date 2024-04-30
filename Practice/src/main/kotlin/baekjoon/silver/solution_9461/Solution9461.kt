package baekjoon.silver.solution_9461


private class Solution9461 {
    fun solution(n: Int): Long {
        val dp = LongArray(n + 1) {
            when (it) {
                1, 2, 3 -> 1
                4, 5 -> 2
                6 -> 3
                7 -> 4
                8 -> 5
                9 -> 7
                10 -> 9
                else -> 0
            }
        }

        for (i in 11 .. n) {
            dp[i] = dp[i - 3] + dp[i - 2]
        }

        return dp[n]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) {
        bw.append("${Solution9461().solution(br.readLine().toInt())}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}